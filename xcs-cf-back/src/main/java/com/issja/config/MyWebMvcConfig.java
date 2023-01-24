package com.issja.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auth ： isSJa
 * @Time ： 2023/1/16 13:21
 **/
@Configuration
public class MyWebMvcConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            // 同源策略
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                // 添加映射路径
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")      // 放行哪些域名，可以多个
                        .allowCredentials(true)                             // 是否发送Cookie信息
                        .allowedMethods("GET", "POST", "PUT", "DELETE")     // 放行哪些请求方式
                        .allowedHeaders("*")                                // 放行哪些原始域(头部信息)
                        .maxAge(3600);                                      // 预请求的结果有效期，默认1800分钟,3600是一小时
            }

            // 文件虚拟路径
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/file/**").addResourceLocations("file:F:/projs/files/avatar/");
            }
        };
    }
}
