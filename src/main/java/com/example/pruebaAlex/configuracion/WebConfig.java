package com.example.pruebaAlex.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir solicitudes de http://localhost:4200 para todas las rutas que comienzan con /api
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")  // Permitir desde tu aplicación Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*")  // Permitir cualquier encabezado
                .allowCredentials(true); // Si necesitas permitir cookies/credenciales
    }
}
