package co.edu.icesi.dev.uccareapp.transport.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.ShipmethodDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.VendorDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@Rollback
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseorderheaderDAOTest {

    @Autowired
    PurchaseorderheaderDAO purchaseorderheaderDAO;

    @Autowired
    ShipmethodDAO shipmethodDAO;

    @Autowired
    VendorDAO vendorDAO;

    @BeforeAll
    public void beforeAll() {
        assertNotNull(purchaseorderheaderDAO);

        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(1);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("FOUR");

        shipmethodDAO.save(shipmethod);

        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(1);
        vendor.setCreditrating(1);
        vendor.setPurchasingwebserviceurl("https:");
        vendor.setName("test");
        
        vendorDAO.save(vendor);



        Purchaseorderheader purchaseorderheader1 = new Purchaseorderheader();
        purchaseorderheader1.setPurchaseorderid(1);
        purchaseorderheader1.setEmployeeid(1);
        purchaseorderheader1.setOrderdate(LocalDate.now());
        purchaseorderheader1.setSubtotal(new BigDecimal("0.1"));
        purchaseorderheader1.setShipmethod(shipmethod);

        Purchaseorderheader purchaseorderheader2 = new Purchaseorderheader();
        purchaseorderheader2.setPurchaseorderid(2);
        purchaseorderheader2.setEmployeeid(2);
        purchaseorderheader2.setOrderdate(LocalDate.now());
        purchaseorderheader2.setSubtotal(new BigDecimal("0.1"));
        purchaseorderheader2.setVendor(vendor);

        Purchaseorderheader purchaseorderheader3 = new Purchaseorderheader();
        purchaseorderheader3.setPurchaseorderid(3);
        purchaseorderheader3.setEmployeeid(3);
        purchaseorderheader3.setOrderdate(LocalDate.now());
        purchaseorderheader3.setSubtotal(new BigDecimal("0.1"));
        purchaseorderheader3.setShipmethod(shipmethod);

        purchaseorderheaderDAO.save(purchaseorderheader1);
        purchaseorderheaderDAO.save(purchaseorderheader2);
        purchaseorderheaderDAO.save(purchaseorderheader3);
    }

    @Nested
    @DisplayName("Basic Cases")
    class Basic {    
        @Test
        @Transactional(rollbackFor = Exception.class)
        public void saveTest() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(0);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));
    
            purchaseorderheaderDAO.save(purchaseorderheader);

            assertEquals(purchaseorderheader, purchaseorderheaderDAO.findById(0).get());
        }

        @Test
        @Transactional(rollbackFor = Exception.class)
        public void updateTest() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            purchaseorderheaderDAO.update(purchaseorderheader);

            assertEquals(purchaseorderheader.getEmployeeid(), 
                purchaseorderheaderDAO.findById(1).get().getEmployeeid());
        }
    
        @Test
        @Transactional(rollbackFor = Exception.class)
        public void deleteTest() {
            Purchaseorderheader purchaseorderheader = purchaseorderheaderDAO.findById(1).get();
            
            purchaseorderheaderDAO.delete(purchaseorderheader);

            assertTrue(purchaseorderheaderDAO.findById(1).isEmpty());
        }
    
        @Test
        @Transactional(rollbackFor = Exception.class)
        public void deleteAllTest() {
            purchaseorderheaderDAO.deleteAll();

            assertEquals(0, purchaseorderheaderDAO.findAll().spliterator().estimateSize());
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findByIdTest() {
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(0);
            purchaseorderheader.setEmployeeid(100);
            purchaseorderheader.setOrderdate(LocalDate.now());
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            purchaseorderheaderDAO.save(purchaseorderheader);

            assertEquals(purchaseorderheader.getEmployeeid(), purchaseorderheaderDAO.findById(0).get().getEmployeeid());
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findAllTest() {
            assertEquals(3, purchaseorderheaderDAO.findAll().spliterator().estimateSize());
        }
    }

    @Nested
    @DisplayName("JPQL Cases")
    class JPQL {
        @Test
        @Transactional(readOnly = true)
        public void findByShipmethodid() {
            long result = purchaseorderheaderDAO.findByShipmethodid(1).spliterator().estimateSize();
            assertEquals(2, result);
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findByVendorid() {
            long result = purchaseorderheaderDAO.findByVendorid(1).spliterator().estimateSize();
            assertEquals(1, result);
        }
    
        @Test
        @Transactional(readOnly = true)
        public void findByDateRange() {
            
        }
    }
}