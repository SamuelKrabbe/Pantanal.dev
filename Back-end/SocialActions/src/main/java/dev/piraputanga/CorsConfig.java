package main.resources;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");  // Adjust the mapping URL as needed
                // .allowedOrigins("http://127.0.0.1:5500/") // Allow specific origin
                // .allowedMethods("GET", "POST")  // Allow specific HTTP methods
                // .allowedHeaders("Content-Type");  // Allow specific headers
    }
}
