package com.example.florai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // 인증 정보 포함 허용
        config.setAllowedOrigins(Arrays.asList("http://192.168.219.42:3000","http://localhost:3000")); // 프론트 주소
        config.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더 허용
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 모든 메서드 허용
        config.setExposedHeaders(Arrays.asList("*")); // 응답 헤더 노출

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 적용
        return new CorsFilter(source);
    }
}
