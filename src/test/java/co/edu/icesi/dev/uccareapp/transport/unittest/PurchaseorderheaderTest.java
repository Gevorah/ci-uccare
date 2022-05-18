package co.edu.icesi.dev.uccareapp.transport.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAOImp;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class PurchaseorderheaderTest {
    
    @Mock
    private PurchaseorderheaderDAOImp purchaseorderheaderdao;

    @Mock
    private EmployeeRepository employeerepository;
    
    @InjectMocks
    private PurchaseorderheaderServiceImp purchaseorderheaderservice;

    @BeforeAll
    public static void breforeAll() {
        System.out.println("--------->SETUP<---------");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("--------->DESTROY<---------");
    }

    @Nested
    @DisplayName("Save Cases")
    class Save {
        @Test
        @DisplayName("save purchaseorderheader 1")
        public void savePurchaseorderheaderTest1() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);
             
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now());
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            assertTrue(purchaseorderheaderservice.savePurchaseorderheader(optpurchaseorderheader.get()));
            assertEquals(optpurchaseorderheader, purchaseorderheaderdao.findById(1));
        }
        
        @Test
        @DisplayName("save purchaseorderheader 2")
        public void savePurchaseorderheaderTest2() {
            Employee employee = new Employee();
            employee.setBusinessentityid(3);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now());
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("employeeid doesn't correspond to an existing Employee", thrown.getMessage());
        }
        
        @Test
        @DisplayName("save purchaseorderheader 3")
        public void savePurchaseorderheaderTest3() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now().minusDays(1));
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }
        
        @Test
        @DisplayName("save purchaseorderheader 4")
        public void savePurchaseorderheaderTest4() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now().plusDays(1));
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }

        
        @Test
        @DisplayName("save purchaseorderheader 5")
        public void savePurchaseorderheaderTest5() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now());
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("-0.1"));

            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.savePurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("subtotal must be greater than 0", thrown.getMessage());
        }
    }

    @Nested
    @DisplayName("Edit Cases")
    class Edit {
        @Test
        @DisplayName("edit purchaseorderheader 1")
        public void editPurchaseorderheaderTest1() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now());
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            assertTrue(purchaseorderheaderservice.editPurchaseorderheader(optpurchaseorderheader.get()));
            assertEquals(optpurchaseorderheader, purchaseorderheaderdao.findById(1));
        }

        @Test
        @DisplayName("edit purchaseorderheader 2")
        public void editPurchaseorderheaderTest2() {
            Employee employee = new Employee();
            employee.setBusinessentityid(3);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);          
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now());
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("employeeid doesn't correspond to an existing Employee", thrown.getMessage());
        }
        
        @Test
        @DisplayName("edit purchaseorderheader 3")
        public void editPurchaseorderheaderTest3() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);            
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now().minusDays(1));
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }
        
        @Test
        @DisplayName("edit purchaseorderheader 4")
        public void editPurchaseorderheaderTest4() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);            
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now().plusDays(1));
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("0.1"));

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("orderdate must be actual", thrown.getMessage());
        }

        
        @Test
        @DisplayName("edit purchaseorderheader 5")
        public void editPurchaseorderheaderTest5() {
            Employee employee = new Employee();
            employee.setBusinessentityid(2);

            when(employeerepository.existsById(2)).thenReturn(true);

            Optional<Purchaseorderheader> optpurchaseorderheader = Optional.of(new Purchaseorderheader());
            optpurchaseorderheader.get().setPurchaseorderid(1);
            optpurchaseorderheader.get().setEmployeeid(employee.getBusinessentityid());
            optpurchaseorderheader.get().setOrderdate(LocalDate.now());
            optpurchaseorderheader.get().setSubtotal(new BigDecimal("-0.1"));

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(optpurchaseorderheader);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderheaderservice.editPurchaseorderheader(optpurchaseorderheader.get());
            });
            assertEquals("subtotal must be greater than 0", thrown.getMessage());
        }
    }
}
