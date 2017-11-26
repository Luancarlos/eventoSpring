package br.com.tete.teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TesteApplication {

	public static void main(String[] args) {

		SpringApplication.run(TesteApplication.class, args);

	}
}
