package it.lorenzo.app.securityconf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	@Autowired
	private MyAppUserDetailsService myAppUserDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/static/**","/font/**", 
						"/booking/barber/registrazione", "/rest/**")
				.permitAll() //Permette a tutti di accedere a queste risorde
				.antMatchers("/booking/barber/**").hasAnyRole("ADMIN", "USER")
				.and().formLogin().loginPage("/login") //Permette a tutti di accedere a queste risorse se e soltanto se si è effettuati i login e si ha ruolo ADMIN o USER
				.loginPage("/").loginProcessingUrl("/home").usernameParameter("username").passwordParameter("password") // Chiamata al DB con username e password inserite
				.defaultSuccessUrl("/homepage")
				.and().logout() // logout
				.logoutUrl("/logout").logoutSuccessUrl("/login")
				.and().csrf().disable();
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/**").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowedOrigins("http://localhost:8080")
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "Accept", "X-Requested-With", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin")
            .exposedHeaders("Access-Control-Expose-Headers", "Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin");

    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
}