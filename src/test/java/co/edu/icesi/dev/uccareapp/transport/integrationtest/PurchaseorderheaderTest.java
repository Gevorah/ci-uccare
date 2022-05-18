package co.edu.icesi.dev.uccareapp.transport.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAO;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseorderheaderTest { 
    @Autowired
    PurchaseorderheaderDAO purchaseorderheaderDAO;

    @Autowired
    EmployeeRepository employeerepository;

    @Autowired
    PurchaseorderheaderService purchaseorderheaderservice;

    @BeforeAll
    public void setUp() {
        System.out.println("--------->SETUP<---------");
        
        Employee employee = new Employee();
        employee.setBusinessentityid(1);
        employeerepository.save(employee);
        
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(1);
        purchaseorderheader.setEmployeeid(1);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));
        purchaseorderheaderDAO.save(purchaseorderheader);
    }

    @AfterAll
    public void afterAll() {
        System.out.println("--------->DESTROY<---------");
        
        //purchaseorderheaderDAO.deleteAll();
        //employeerepository.deleteAll();
    }

    @Nested
    @DisplayName("Save Cases")
    class Save {
        @BeforeEach
        public void beforeEach() {
            purchaseorderheaderDAO.deleteAll();
        }

        @Test
        @DisplayName("save purchaseorderheader test 1")
        public void savePurchaseorderheadertest1() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            assertTrue(purchaseorderheaderservice.savePurchaseorderheader(purchaseorderheader));
        }

        @Test
        @DisplayName("save purchaseorderheader test 2")
        public void savePurchaseorderheadertest2() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(3);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(purchaseorderheader);
            });
            assertEquals("employeeid doesn't correspond to an existing Employee", thrown.getMessage());
        }

        @Test
        @DisplayName("save purchaseorderheader test 3")
        public void savePurchaseorderheadertest3() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now().minusDays(1));
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(purchaseorderheader);
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }

        @Test
        @DisplayName("save purchaseorderheader test 4")
        public void savePurchaseorderheadertest4() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now().plusDays(1));
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(purchaseorderheader);
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }

        @Test
        @DisplayName("save purchaseorderheader test 5")
        public void savePurchaseorderheadertest5() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("-0.1"));

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(purchaseorderheader);
            });
            assertEquals("subtotal must be greater than 0", thrown.getMessage());
        }
    }

    @Nested
    @DisplayName("Edit Cases")
    class Edit {
        @Test
        @DisplayName("edit purchaseorderheader test 1")
        public void editPurchaseorderheadertest1() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            assertTrue(purchaseorderheaderservice.editPurchaseorderheader(purchaseorderheader));
        }

        @Test
        @DisplayName("edit purchaseorderheader test 2")
        public void editPurchaseorderheadertest2() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(3);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(purchaseorderheader);
            });
            assertEquals("employeeid doesn't correspond to an existing Employee", thrown.getMessage());
        }

        @Test
        @DisplayName("edit purchaseorderheader test 3")
        public void editPurchaseorderheadertest3() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now().minusDays(1));
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(purchaseorderheader);
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }

        @Test
        @DisplayName("edit purchaseorderheader test 4")
        public void editPurchaseorderheadertest4() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now().plusDays(1));
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(purchaseorderheader);
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }

        @Test
        @DisplayName("edit purchaseorderheader test 5")
        public void editPurchaseorderheadertest5() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("-0.1"));

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(purchaseorderheader);
            });
            assertEquals("subtotal must be greater than 0", thrown.getMessage());
        }
    }
}