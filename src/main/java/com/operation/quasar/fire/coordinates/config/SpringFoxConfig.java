package com.operation.quasar.fire.coordinates.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.operation.quasar.fire.coordinates.controllers"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(getApiInfo());
    }
    
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Operation Quasar Fire - Service API",
				"help transport rations and weaponry for the entire legion",
				"1.0",
				"https://github.com/maylcon",
				new Contact("Maylcon Ramirez", "https://github.com/maylcon/operation-quasar-fire", "sanson.saray@gmail.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
    

}