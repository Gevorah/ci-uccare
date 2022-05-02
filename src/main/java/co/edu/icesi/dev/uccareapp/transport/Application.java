package co.edu.icesi.dev.uccareapp.transport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;
import co.edu.icesi.dev.uccareapp.transport.security.Role;
import co.edu.icesi.dev.uccareapp.transport.security.User;

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
		BusinessentityRepository businessentityrepository, EmployeeRepository employeerepository) {
		return (args) -> {
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword("{noop}admin");
			admin.setRole(Role.ADMIN);
			userrepository.save(admin);

			User operator = new User();
			operator.setUsername("operator");
			operator.setPassword("{noop}123");
			operator.setRole(Role.OPERATOR);
			userrepository.save(operator);

			Businessentity businessentity1 = new Businessentity();
			businessentity1.setBusinessentityid(1);
			businessentityrepository.save(businessentity1);

			Businessentity businessentity2 = new Businessentity();
			businessentity2.setBusinessentityid(2);
			businessentityrepository.save(businessentity2);

			Employee employee1 = new Employee();
			employee1.setBusinessentityid(1);
			employeerepository.save(employee1);

			Employee employee2 = new Employee();
			employee2.setBusinessentityid(2);
			employeerepository.save(employee2);

		};
	}
}


