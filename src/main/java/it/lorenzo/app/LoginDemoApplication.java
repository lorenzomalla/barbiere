package it.lorenzo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = { "it.lorenzo.app.*" })
@PropertySources({
		@PropertySource({ "classpath:application-${spring.profiles.active}.properties",
				"classpath:application-${spring.profiles.active}.properties" }),
		@PropertySource("classpath:application.properties") })
public class LoginDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginDemoApplication.class, args);
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
