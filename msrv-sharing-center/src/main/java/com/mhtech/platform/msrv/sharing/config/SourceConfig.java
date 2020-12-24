package com.mhtech.platform.msrv.sharing.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configuration
@PropertySource(value = { "classpath:META-INF/system-unmodified.properties",
		"classpath:META-INF/message_zh_CN.properties" }, ignoreResourceNotFound = true, encoding = "UTF-8")
@ImportResource(value = "classpath:dubbo-provider.xml")
public class SourceConfig {

	/**
	 * 使用注解直接读取配置文件中的日期类型
	 * 
	 * @return 格式转换器
	 */
	@Bean
	public ConversionService conversionService() {
		FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setUseIsoFormat(true);
		factory.setFormatterRegistrars(Collections.singleton(registrar));
		factory.afterPropertiesSet();
		return factory.getObject();
	}
}
