package com.mhtech.platform.msrv.sharing.redis;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 初始化RedisTemplate
 * @author GM
 */
@Configuration
public class RedisConfiguration {
	
	@Value("${actionLogFilter.userAccessLog}")
	private String userAccessLog;
	
	@Value("${monitor-server-alerts}")
	private String monitorServerAlerts;

	/**
	 * 初始化redis操作模板
	 * @param factory
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisTemplate<String, Object> redisTemplate(
			RedisConnectionFactory factory
			) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
	@Bean
	@Primary
	public RedisConnectionFactory lettuceConnectionFactory(
			LettucePoolingClientConfiguration poolConfig,
			@Value("${spring.redis.host}") String host,
			@Value("${spring.redis.port}") int port,
			@Value("${spring.redis.database}") int database,
			@Value("${spring.redis.password}") String password
			) {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setDatabase(database);
        configuration.setPassword(RedisPassword.of(password));
		RedisConnectionFactory factory = new LettuceConnectionFactory(configuration, poolConfig);
		return factory;
	}
	
	@Bean
	@Primary
    public LettucePoolingClientConfiguration getPoolConfig(
    		@Value("${spring.redis.lettuce.pool.max-active}") int maxTotal,
    		@Value("${spring.redis.lettuce.pool.max-wait}") long maxWait,
    		@Value("${spring.redis.lettuce.pool.max-idle}") int maxIdle,
    		@Value("${spring.redis.lettuce.pool.min-idle}") int minIdle,
    		@Value("${spring.redis.lettuce.timeout}") long timeoutMills,
    		@Value("${spring.redis.lettuce.pool.shutdown-timeout}") long shutdownMills,
    		@Value("${spring.redis.lettuce.pool.timeBetweenEvictionRunsMillis}") long timeBetweenEvictionRunsMillis,
    		@Value("${spring.redis.lettuce.pool.minEvictableIdleTimeMillis}") long minEvictableIdleTimeMillis,
    		@Value("${spring.redis.lettuce.pool.numTestsPerEvictionRun}") int numTestsPerEvictionRun) {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWait);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
		config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        LettucePoolingClientConfiguration pool = LettucePoolingClientConfiguration.builder()
                .poolConfig(config)
                .commandTimeout(Duration.ofMillis(timeoutMills))
                .shutdownTimeout(Duration.ofMillis(shutdownMills))
                .build();
        return pool;
    }
	
	@Bean
	@ConditionalOnMissingBean
	public StringRedisTemplate stringRedisTemplate(
			RedisConnectionFactory factory
			) {
		StringRedisTemplate srt = new StringRedisTemplate();
		srt.setConnectionFactory(factory);
		return srt;
	}
	

	/**
	 * 消息监听容器
	 * @param connectionFactory
	 * @param gatewayListenerAdapter 网关日志适配器
	 * @return container
	 */
	@Bean
	public RedisMessageListenerContainer container(
			RedisConnectionFactory connectionFactory , 
			MessageListenerAdapter accessLogHandler,
			MessageListenerAdapter monitorAlertHandler) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		// 针对每一个消息处理可以设置不同的序列化方式

		//处理用户请求日志
		accessLogHandler.setSerializer(jackson2JsonRedisSerializer);
		container.addMessageListener(accessLogHandler, new PatternTopic(userAccessLog));
		
		monitorAlertHandler.setSerializer(jackson2JsonRedisSerializer);
		container.addMessageListener(monitorAlertHandler, new PatternTopic(monitorServerAlerts));
		return container;
	}
	

	/**
	 *	处理用户请求日志
	 */
	@Bean
	public MessageListenerAdapter accessLogHandler(AccessLogsHandler subscriber) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(subscriber, "accessLogHandler");
		return adapter;
	}
	@Bean
	public AccessLogsHandler getAccessLogsHandler() {
		return new AccessLogsHandler();
	}
	
	@Bean
	public MessageListenerAdapter monitorAlertHandler(MonitorAlertHandler monitorAlertHandler) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(monitorAlertHandler, "alert");
		return adapter;
	}
	
	@Bean
	public MonitorAlertHandler getMonitorAlertHandler() {
		return new MonitorAlertHandler();
	}
}
