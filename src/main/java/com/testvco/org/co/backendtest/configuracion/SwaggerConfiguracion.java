package com.testvco.org.co.backendtest.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de SwaggerConfiguracion que contiene la configuración para el swagger
 * 
 * @Author 2023-02-24.celf
 */
@Configuration //Cuando la arquitectura springboot arranca, todo lo que tenga este decorador, orquestara todo a esta arquitectura
@EnableSwagger2
public class SwaggerConfiguracion {
    
    @Bean
    public Docket usersApi(){
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(usersApiInfo());
    }

    /*
     * Se cambio algo 
     * @Modify 2023-02-27.wqel
     */
    private ApiInfo usersApiInfo(){
        return new ApiInfoBuilder()
        .title("Documentacion de capa de servicios Api Rest para la aplicacion ")
        .description("testvi Rest")
        .version("1.0")
        .license("Apache License Version 2.0").build();
    }
}
