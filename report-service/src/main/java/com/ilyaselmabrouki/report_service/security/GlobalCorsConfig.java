package com.ilyaselmabrouki.report_service.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");  // Allow all origins
        corsConfig.addAllowedHeader("*");  // Allow all headers
        corsConfig.addAllowedMethod("*");  // Allow all HTTP methods
        corsConfig.setAllowCredentials(true);  // Allow credentials (for cookies, tokens)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Apply to all paths

        return new CorsFilter(source);
    }
}
