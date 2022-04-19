package co.edu.icesi.dev.uccareapp.transport.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.VendorRepository;
import co.edu.icesi.dev.uccareapp.transport.service.VendorService;
import co.edu.icesi.dev.uccareapp.transport.service.VendorServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class VendorTest {
    @Autowired
    private VendorRepository vendorrepository;

    @Autowired
    private BusinessentityRepository businessentityrepository;

    @Autowired
    private VendorService vendorservice;

    @Autowired
    public VendorTest() {
        this.vendorservice = new VendorServiceImp(vendorrepository, businessentityrepository);
    }

    @BeforeAll
    public static void setUp() {
        System.out.println("SETUP");
    }

    @BeforeEach
    public void beforeEach() {
        Businessentity businessentity = new Businessentity();
        businessentity.setBusinessentityid(1);
        businessentityrepository.save(businessentity);
    }

    @Nested
    @DisplayName("Save Cases")
    public class Save {
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
        	Vendor editvendor = new Vendor();
            editvendor.setBusinessentityid(1);
            editvendor.setCreditrating(1);
            editvendor.setPurchasingwebserviceurl("https");
            editvendor.setName("test");

            vendorservice.saveVendor(editvendor);
            
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
        	Vendor editvendor = new Vendor();
            editvendor.setBusinessentityid(1);
            editvendor.setCreditrating(1);
            editvendor.setPurchasingwebserviceurl("https");
            editvendor.setName("test");

            vendorservice.saveVendor(editvendor);
            
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(3);
            vendor.setCreditrating(1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");

            // IllegalArgumentException thrown =
            // assertThrows(IllegalArgumentException.class, () -> {
            // vendorservice.editVendor(vendor); });
            // assertEquals("", thrown.getMessage());
        }

        @Test
        @DisplayName("edit vendor test 3")
        public void editVendorTest3() {
        	Vendor editvendor = new Vendor();
            editvendor.setBusinessentityid(1);
            editvendor.setCreditrating(1);
            editvendor.setPurchasingwebserviceurl("https");
            editvendor.setName("test");

            vendorservice.saveVendor(editvendor);
            
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(-1);
            vendor.setPurchasingwebserviceurl("https");
            vendor.setName("test");
            
            Iterator<Vendor> t = vendorrepository.findAll().iterator();
            while(t.hasNext()) System.out.println(t.next().getBusinessentityid());
            
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.editVendor(vendor);
            });
            assertEquals("creditrating must be greater than 0", thrown.getMessage());
        }

        @Test
        @DisplayName("edit vendor test 4")
        public void editVendorTest4() {
        	Vendor editvendor = new Vendor();
            editvendor.setBusinessentityid(1);
            editvendor.setCreditrating(1);
            editvendor.setPurchasingwebserviceurl("https");
            editvendor.setName("test");

            vendorservice.saveVendor(editvendor);
            
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
        	Vendor editvendor = new Vendor();
            editvendor.setBusinessentityid(1);
            editvendor.setCreditrating(1);
            editvendor.setPurchasingwebserviceurl("https");
            editvendor.setName("test");

            vendorservice.saveVendor(editvendor);
            
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

    @AfterEach
    public void afterEach() {
        //businessentityrepository.deleteAll();
        vendorrepository.deleteAll();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("DESTROY");
    }
}
