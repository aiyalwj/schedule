package com.lwj.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *  Swagger配置文件
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lwj.schedule"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(" Intelligent shift scheduling system APIs")
                .description("Intelligent shift scheduling system APIs")
                .termsOfServiceUrl("http://localhost:8080/")
//                .contact("long")

//                .contact(new Contact("aiyalwj", "http://localhost:8080/", "wz22261@163.com"))
                .version("1.0")
                .build();
    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars.springfox-swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars.springfox-swagger-ui");
//        super.addResourceHandlers(registry);
//    }
}

