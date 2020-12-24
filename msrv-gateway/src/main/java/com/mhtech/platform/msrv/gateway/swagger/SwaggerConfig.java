package com.mhtech.platform.msrv.gateway.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix="swagger.ui", name="show", havingValue="true")
public class SwaggerConfig extends WebMvcConfigurationSupport {

	//定义分隔符
	private static final String SEPARATE = ";";
	 
    @Bean
    public Docket createRestfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.mhtech.platform.msrv.gateway.controller.monitor"))
                .apis(basePackage("com.mhtech.platform.msrv.gateway.controller.monitor"+SEPARATE+"com.mhtech.platform.msrv.gateway.controller.algorithm"))
                .paths(PathSelectors.any())
                .build();
    }

    private Predicate<RequestHandler> basePackage(final  String basePackage) {
    	 return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
	}
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SEPARATE)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("浙江墨煌科技信息技术有限公司")
                .description("开放文档")
                .version("0.0.1")
                .build();
    }

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 解决静态资源无法访问
		registry.addResourceHandler("/**").addResourceLocations(
				"classpath:/static/");
		// 解决swagger无法访问
		registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations(
				"classpath:/META-INF/resources/");
		// 解决swagger的js文件无法访问
		registry.addResourceHandler("/webjars/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/");
	}
}
