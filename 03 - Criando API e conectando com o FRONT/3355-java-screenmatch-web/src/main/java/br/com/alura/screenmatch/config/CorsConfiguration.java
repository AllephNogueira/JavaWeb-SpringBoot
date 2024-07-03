package br.com.alura.screenmatch.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Para indicar pro spring que essa é uma classe de configuração.
public class CorsConfiguration implements WebMvcConfigurer {
// Implementa o WebMVCConfigurer

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"); // Oque vai poder fazer.
        // Tipo de metodo que vai poder acessar nosso back-end

    }


}
