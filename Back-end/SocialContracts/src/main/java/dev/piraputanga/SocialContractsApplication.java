package dev.piraputanga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Microserviço de contratos sociais", description = "Documentação do serviço de contratos sociais"))
@ComponentScan("dev.piraputanga")
public class SocialContractsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialContractsApplication.class, args);
	}

}
