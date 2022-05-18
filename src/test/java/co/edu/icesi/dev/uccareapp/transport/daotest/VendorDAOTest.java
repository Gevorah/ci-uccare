package co.edu.icesi.dev.uccareapp.transport.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.VendorDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@Rollback
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VendorDAOTest {

    @Autowired
    private VendorDAO vendorDAO;

    @BeforeAll
    public void beforeAll() {
        assertNotNull(vendorDAO);

        Vendor vendor1 = new Vendor();
        vendor1.setBusinessentityid(1);
        vendor1.setCreditrating(10);
        vendor1.setPurchasingwebserviceurl("https:");
        vendor1.setName("vendor");
        vendor1.setPreferredvendorstatus("active");

        Vendor vendor2 = new Vendor();
        vendor2.setBusinessentityid(2);
        vendor2.setCreditrating(20);
        vendor2.setPurchasingwebserviceurl("https:");
        vendor2.setName("vendor");
        vendor2.setPreferredvendorstatus("away");

        Vendor vendor3 = new Vendor();
        vendor3.setBusinessentityid(3);
        vendor3.setCreditrating(30);
        vendor3.setPurchasingwebserviceurl("https:");
        vendor3.setName("vendor");
        vendor3.setPreferredvendorstatus("active");

        vendorDAO.save(vendor1);
        vendorDAO.save(vendor2);
        vendorDAO.save(vendor3);
    }

    @Nested
    @DisplayName("Basic Cases")
    class Basic {    
        @Test
        @Transactional(rollbackFor = Exception.class)
        public void saveTest() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(0);
            vendor.setCreditrating(10);
            vendor.setPurchasingwebserviceurl("https:");
            vendor.setName("test");
    
            vendorDAO.save(vendor);

            assertEquals(vendor, vendorDAO.findById(0).get());
        }

        @Test
        @Transactional(rollbackFor = Exception.class)
        public void updateTest() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);
            vendor.setCreditrating(10);
            vendor.setPurchasingwebserviceurl("https:");
            vendor.setName("test");

            vendorDAO.update(vendor);

            assertEquals(vendor.getName(), vendorDAO.findById(1).get().getName());
        }
    
        @Test
        @Transactional(rollbackFor = Exception.class)
        public void deleteTest() {
            Vendor vendor = vendorDAO.findById(1).get();
            
            vendorDAO.delete(vendor);

            assertTrue(vendorDAO.findById(1).isEmpty());
        }
    
        @Test
        @Transactional(rollbackFor = Exception.class)
        public void deleteAllTest() {
            vendorDAO.deleteAll();

            assertEquals(0, vendorDAO.findAll().spliterator().estimateSize());
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findByIdTest() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(0);
            vendor.setCreditrating(10);
            vendor.setPurchasingwebserviceurl("https:");
            vendor.setName("gevorah");

            vendorDAO.save(vendor);

            assertEquals(vendor.getName(), vendorDAO.findById(0).get().getName());
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findAllTest() {
            assertEquals(3, vendorDAO.findAll().spliterator().estimateSize());
        }
    }

    @Nested
    @DisplayName("JPQL Cases")
    class JPQL {
    
        @Test
        @Transactional(readOnly = true)
        public void findByCreditratingTest() {
            Vendor vendor = vendorDAO.findByCreditrating(10).iterator().next();
            assertEquals(1, vendor.getBusinessentityid());
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findByNameTest() {
            long vendors = vendorDAO.findByName("vendor").spliterator().estimateSize();
            assertEquals(3, vendors);
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findByPreferredvendorstatusTest() {
            long vendors = vendorDAO.findByPreferredvendorstatus("active").spliterator().estimateSize();
            assertEquals(2, vendors);
        }
    }
}