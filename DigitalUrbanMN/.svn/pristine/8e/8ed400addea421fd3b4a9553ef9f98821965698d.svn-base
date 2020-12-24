package com.mhtech.platform.msrv.pojo;


/**
 * 监控服务信息
 * @author GM
 */
public interface MonitorServer {

	/**
	 * 服务类型
	 * @author GM
	 */
	public static enum ServerType {
		HARDWARE(100, "硬件服务"),
		SOFTWARE(200, "软件服务");
		
		public static ServerType valueOf(int code) {
			ServerType[] servers = values();
			for (ServerType server : servers) {
				if(server.code == code) {
					return server;
				}
			}
			return null;
		} 
		
		private ServerType(int code, String type) {
			this.code = code;
			this.type = type;
		}
		
		private int code;
		private String type;
		public int getCode() {
			return code;
		}
		public String getType() {
			return type;
		}
	}
	
	/**
	 * 服务编码前缀
	 * @author GM
	 */
	public static enum PrefixCode {
		FW("fw"),
		YY("yy");
		
		private String prefix;
		private PrefixCode(String prefix) {
			this.prefix = prefix;
		}
		public String getPrefix() {
			return prefix;
		}
	}
}
