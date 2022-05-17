package co.edu.icesi.dev.uccareapp.transport.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.VendorDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.service.VendorService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VendorTest {
    @Autowired
    private VendorDAO vendorrepository;

    @Autowired
    private BusinessentityRepository businessentityrepository;

    @Autowired
    private VendorService vendorservice;

    @BeforeAll
    public void beforeAll() {
        System.out.println("--------->SETUP<---------");

        Businessentity businessentity = new Businessentity();
        businessentity.setBusinessentityid(1);
        businessentityrepository.save(businessentity);

        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(1);
        vendor.setCreditrating(1);
        vendor.setPurchasingwebserviceurl("https");
        vendor.setName("test");
        vendorservice.saveVendor(vendor);
    }

    @AfterAll
    public void afterAll() {
        System.out.println("--------->DESTROY<---------");
        
        businessentityrepository.deleteAll();
        
        vendorrepository.deleteAll();
    }

    @Nested
    @DisplayName("Save Cases")
    public class Save {
        @BeforeEach
        public void BeforeEach() {
            vendorrepository.deleteAll();
        }

        @Test
        @DisplayName("save vendor test 1")
        public void saveVendorTest1() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");

            assertTrue(vendorservice.saveVendor(vendor));
        }

        @Test
        @DisplayName("save vendor test 2")
        public void saveVendorTest2() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(3);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            	vendorservice.saveVendor(vendor);
            });
            assertEquals("Businessentity doesn't exist", thrown.getMessage());
        }

        @Test
        @DisplayName("save vendor test 3")
        public void saveVendorTest3() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(-1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.saveVendor(vendor);
            });
            assertEquals("creditrating must be greater than 0", thrown.getMessage());
        }

        @Test
        @DisplayName("save vendor test 4")
        public void saveVendorTest4() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("http:");
            vendor.setName("test");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.saveVendor(vendor);
            });
            assertEquals("URL must start with 'https'", thrown.getMessage());

        }

        @Test
        @DisplayName("save vendor test 5")
        public void saveVendorTest5() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName(null);

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                vendorservice.saveVendor(vendor);
            });
            assertEquals("Fill the name field", thrown.getMessage());
        }
    }

    @Nested
    @DisplayName("Edit Cases")
    public class Edit {
        @Test
        @DisplayName("edit vendor test 1")
        public void editVendorTest1() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");

            assertTrue(vendorservice.editVendor(vendor));
        }

        @Test
        @DisplayName("edit vendor test 2")
        public void editVendorTest2() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(3);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                vendorservice.editVendor(vendor); 
            });
            assertEquals("Vendor is null or doesn't exist", thrown.getMessage());
        }

        @Test
        @DisplayName("edit vendor test 3")
        public void editVendorTest3() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(-1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");
            
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.editVendor(vendor);
            });
            assertEquals("creditrating must be greater than 0", thrown.getMessage());
        }

        @Test
        @DisplayName("edit vendor test 4")
        public void editVendorTest4() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("http:");
            vendor.setName("test");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.editVendor(vendor);
            });
            assertEquals("URL must start with 'https'", thrown.getMessage());
        }

        @Test
        @DisplayName("edit vendor test 5")
        public void editVendorTest5() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName(null);

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                vendorservice.editVendor(vendor);
            });
            assertEquals("Fill the name field", thrown.getMessage());
        }
    }
}
