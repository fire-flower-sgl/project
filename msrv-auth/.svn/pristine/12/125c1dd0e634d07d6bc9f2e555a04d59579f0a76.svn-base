package com.mhtech.platform.msrv.auth.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static final String DATE_FIELD_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private JsonUtils() {}
	
	/**
	 * 对象序列化成json串
	 * @param object
	 * @return 返回的字符串不包含NULL值属性、字段名为下划线格式、日期格式为<code>yyyy-MM-dd HH:mm:ss</code>
	 */
	public static <T> String object2DefaultJson(T object) {
		String json = "";
		if(Objects.isNull(object)) {
			return json;
		}
		try {
			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			SimpleDateFormat fmt = new SimpleDateFormat(DATE_FIELD_PATTERN);
			om.setDateFormat(fmt);
			json = om.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("序列化失败", e);
		}
		return json;
	}
	
	/**
	 * 转换成泛型对象
	 * @param jsonString
	 * @param clazz
	 * @param genericClazz
	 * @param jsonNamingStrategy
	 * @return
	 */
	public static <T, G> T json2GenericBean(String jsonString, Class<T> clazz,
			Class<G> genericClazz) {
		T entity = null;
		if (null == jsonString || "".equals(jsonString)) {
			return entity;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			SimpleDateFormat fmt = new SimpleDateFormat(DATE_FIELD_PATTERN);
			mapper.setDateFormat(fmt);
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,
					true);
			// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			JavaType type= mapper.getTypeFactory().constructParametricType(clazz, genericClazz);
			entity = mapper.readValue(jsonString, type);
		} catch (Exception e) {
			logger.error("反序列化失败", e);
		}
		return entity;
	}
	
	/**
	 * 反序列化成对象
	 * @param jsonString
	 * @param clazz
	 * @return 
	 */
	public static <T> T json2DefaultBean(String jsonString, Class<T> clazz) {
		T entity = null;
		if (null == jsonString || "".equals(jsonString)) {
			return entity;
		}
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FIELD_PATTERN);
		mapper.setDateFormat(fmt);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,
				true);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			entity = mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			logger.error("反序列化失败", e);
		}
		return entity;
	}
	
	/**
	 * 集合泛型转换
	 * @param source 原集合
	 * @param trgClazz 新类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <C extends Collection<?>, T> Collection<T> transfCollectionsGeneric(C source, Class<T> trgClazz) {
		String json = object2DefaultJson(source);
		return json2GenericBean(json, source.getClass(), trgClazz);
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static enum JsonNamingStrategy {
		KEBAB_CASE("KEBAB_CASE"), LOWER_CAMEL_CASE("LOWER_CAMEL_CASE"), LOWER_CASE(
				"LOWER_CASE"), SNAKE_CASE("SNAKE_CASE"), UPPER_CAMEL_CASE(
				"SNAKE_CASE"), CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES(
				"CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES");

		private String strategy;

		private PropertyNamingStrategy propertyNamingStrategy;

		private JsonNamingStrategy(String strategy) {
			this.strategy = strategy;
		}

		public String getStrategy() {
			return strategy;
		}

		public PropertyNamingStrategy getPropertyNamingStrategy() {
			if (strategy.equals("KEBAB_CASE"))
				return PropertyNamingStrategy.KEBAB_CASE;
			else if (strategy.equals("LOWER_CAMEL_CASE"))
				return PropertyNamingStrategy.LOWER_CAMEL_CASE;
			else if (strategy.equals("LOWER_CASE"))
				return PropertyNamingStrategy.LOWER_CASE;
			else if (strategy.equals("SNAKE_CASE"))
				return PropertyNamingStrategy.SNAKE_CASE;
			else if (strategy.equals("UPPER_CAMEL_CASE"))
				return PropertyNamingStrategy.UPPER_CAMEL_CASE;
			else if (strategy
					.equals("CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES"))
				return PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;
			return null;
		}
	}
}
