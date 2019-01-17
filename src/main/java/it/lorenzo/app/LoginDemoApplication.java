package it.lorenzo.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@PropertySources({ @PropertySource({ "classpath:application-repository.properties" }),
//		@PropertySource("classpath:application.properties") })
public class LoginDemoApplication {

	@Value("${prova.variabile}")
	static String prova;

	public static void main(String[] args) {
		SpringApplication.run(LoginDemoApplication.class, args);
		System.out.println("***************************************" + prova);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
