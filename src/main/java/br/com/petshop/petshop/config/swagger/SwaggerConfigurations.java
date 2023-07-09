package br.com.petshop.petshop.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	/**
	 * Para acessar a documentação na url digite IP:PORT/swagger-ui.html
	 * 
	 * localhost:8080/swagger-ui.html
	 * 
	 * */
	
	@Bean
	public Docket petShopApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.petshop.petshop.controller"))
				.paths(PathSelectors.ant("/**"))
				.build();
				//.ignoredParameterTypes(Cliente.class);
	}
}
