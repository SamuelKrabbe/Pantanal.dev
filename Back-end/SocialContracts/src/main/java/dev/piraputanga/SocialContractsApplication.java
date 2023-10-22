package dev.piraputanga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dev.piraputanga")
public class SocialContractsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialContractsApplication.class, args);
	}

}
