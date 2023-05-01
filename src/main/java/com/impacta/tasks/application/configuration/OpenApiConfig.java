package com.impacta.tasks.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	  @Bean
	  public OpenAPI TasksAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Tasks API")
	              .description("Tasks API for university project")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("Tasks API Documentation")
	              .url("https://github.com/gabizinha12/Tasks-API"));
	  }

}
