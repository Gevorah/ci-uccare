package co.edu.icesi.dev.uccareapp.transport;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAO;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;

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
    public CommandLineRunner dummy(BusinessentityRepository businessentityrepository,
        EmployeeRepository employeerepository, PurchaseorderheaderDAO purchaseorderheaderrepository) {
        return (args) -> {

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

            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));
            purchaseorderheaderrepository.save(purchaseorderheader);
        };
    }
}


