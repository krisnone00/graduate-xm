package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1. 允许的域：使用 Pattern 替代 addAllowedOrigin("*")
        corsConfiguration.addAllowedOriginPattern("*");
        // 2. 允许发送 Cookie/凭证 (非常重要，否则前端登录后拿不到 Token)
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");  // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*");  // 3 设置访问源请求方法
        source.registerCorsConfiguration("/**", corsConfiguration);  // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
