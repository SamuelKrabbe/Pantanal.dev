package dev.piraputanga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Microserviço de ações sociais", description = "Documentação do serviço de ações sociais"))
@ComponentScan("dev.piraputanga")
public class SocialActionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialActionsApplication.class, args);
	}

}
