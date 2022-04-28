package co.edu.icesi.dev.uccareapp.transport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.model.login.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.login.UserType;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;

@SpringBootApplication
public class Application {
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner dummy(UserRepository userrepository,
		BusinessentityRepository businessentityrepository) {
		return (args) -> {
			UserApp user = new UserApp();
			user.setUsername("gevorah");
			user.setPassword("{noop}123");
			user.setType(UserType.admin);
			userrepository.save(user);

			Businessentity businessentity = new Businessentity();
			businessentity.setBusinessentityid(1);
			businessentityrepository.save(businessentity);
		};
	}
}


