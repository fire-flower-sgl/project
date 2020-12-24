package com.mhtech.platform.msrv.sharing.config;


/**
 * 雪花算法生成主键ID
 * 【****时钟回拨会出现重复ID****】
 * <p>目前定为面向数据库全局递增</p>
 * <p>
 * <li>第一个部分，是 1 个 bit：0，这个是无意义的；</li>
 * <li>第二个部分是 41 个 bit：表示的是时间戳；</li>
 * <li>第三个部分是 5 个 bit：表示的是机房 id，10001；</li>
 * <li>第四个部分是 5 个 bit：表示的是机器 id，11001；</li>
 * <li>第五个部分是 12 个 bit：表示的序号，就是某个机房某台机器上这一毫秒内同时生成的 id 的序号，0000 00000000；</li>
 * </p>
 * @author GM
 */
public class IdWorker {

	private long workerId; // 机器id
	private long datacenterId; // 机房id
	private long sequence; // 一毫秒内生成的多个id的最新序号
	
	/**
	 * 初始化主键生成规则
	 * @param workerId 业务表ID
	 * @param datacenterId 数据库服务器ID
	 * @param sequence 每毫秒生成上限
	 */
	protected IdWorker(long workerId, long datacenterId, long sequence) {
		if (sequence > (2 << 11) - 1 || sequence < 0) {

			throw new IllegalArgumentException(String.format(
					"sequence can't be greater than %d or less than 0",
					(2 << 11) - 1));
		}
		// sanity check for workerId
		// 传递进来的机房id和机器id不能超过31，不能小于0
		if (workerId > maxWorkerId || workerId < 0) {

			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0",
					maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {

			throw new IllegalArgumentException(String.format(
					"datacenter Id can't be greater than %d or less than 0",
					maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
		this.sequence = sequence;
	}

	/**
	 * 起始时间戳
	 */
	private long twepoch;
	private long workerIdBits = 5L;
	private long datacenterIdBits = 5L;

	// 二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);
	// 5 bit最多只能有31个数字，机房id最多只能是32以内
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private long sequenceBits = 12L;
	private long workerIdShift = sequenceBits;
	private long datacenterIdShift = sequenceBits + workerIdBits;
	private long timestampLeftShift = sequenceBits + workerIdBits
			+ datacenterIdBits;
	private long sequenceMask = -1L ^ (-1L << sequenceBits);
	private long lastTimestamp = -1L;

	public long getWorkerId() {
		return workerId;
	}

	public long getDatacenterId() {
		return datacenterId;
	}

	public long getTimestamp() {
		return System.currentTimeMillis();
	}

	//当前这台机器上的snowflake算法程序生成一个全局唯一的id
	public synchronized long nextId() {
		// 获取当前时间戳，单位是毫秒
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			System.err.printf(
					"clock is moving backwards. Rejecting requests until %d.",
					lastTimestamp);
			throw new RuntimeException(
					String.format(
							"Clock moved backwards. Refusing to generate id for %d milliseconds",
							lastTimestamp - timestamp));
		}

		// 假设在同一个毫秒内，又发送了一个请求生成一个id
		// 这个时候就得把seqence序号给递增1，最多就是4096
		if (lastTimestamp == timestamp) {

			// 一个毫秒内最多只能有4096个数字，无论传递多少进来，
			// 保证始终就是在4096这个范围内，避免传递sequence超过了4096这个范围
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}

		} else {
			sequence = 0;
		}
		// 记录一下最近一次生成id的时间戳，单位是毫秒
		lastTimestamp = timestamp;
		// 核心二进制位运算操作，生成一个64bit的id
		// 先将当前时间戳左移，放到41 bit；将机房id左移放到5 bit；将机器id左移放到5 bit；将序号放最后12 bit
		// 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
		return ((timestamp - twepoch) << timestampLeftShift)
				| (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;
	}

	private long tilNextMillis(long lastTimestamp) {

		long timestamp = timeGen();

		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
	
	protected void setTwepoch(long twepoch) {
		this.twepoch = twepoch;
	}
}
