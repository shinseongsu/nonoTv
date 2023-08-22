package com.nonotv.video.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        return new CorsWebFilter(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(ServerWebExchange exchange) {
                CorsConfiguration config = new CorsConfiguration();
                config.addAllowedOrigin("*"); // 허용할 origin 설정
                config.addAllowedMethod(HttpMethod.GET); // 허용할 HTTP 메서드 설정
                config.addAllowedHeader("*"); // 허용할 헤더 설정
                config.setMaxAge(3600L); // Preflight 요청 캐싱 시간 (1시간)

                return config;
            }
        });
    }
}
