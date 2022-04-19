package co.edu.icesi.dev.uccareapp.transport.integrationtest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.repository.PurchaseorderdetailRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.PurchaseorderheaderRepository;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailService;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailServiceImp;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderService;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class PurchaseorderheaderTest { 
    @Autowired
    PurchaseorderheaderRepository purchaseorderheaderrepository;

    @Autowired
    PurchaseorderheaderService purchaseorderheaderservice;

    @Autowired
    public PurchaseorderheaderTest(PurchaseorderdetailRepository purchaseorderdetailrepository, PurchaseorderheaderRepository purchaseorderheaderrepository) {
        this.purchaseorderheaderservice = new PurchaseorderheaderServiceImp(purchaseorderheaderrepository, vendorrepository, employeerepository)
    }

    @BeforeAll
    public static void setUp() {
        System.out.println("SETUP");
    }

    @Nested
    @DisplayName("Save Cases")
    class Save {
        @Test
        @DisplayName("save purchaseorderdetail test")
        public void savePurchaseorderdetailtest1() {
            Vendor vendor = new Vendor();
            vendor.setBusinessentityid(1);

            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);
            purchaseorderheader.setVendor(vendor);
            purchaseorderheader.setEmployeeid(1);
            purchaseorderheader.setOrderdate(Timestamp.from(Instant.now()));
            purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

            assertTrue(purchaseorderhe);
        }

    }

    @Nested
    @DisplayName("Edit Cases")
    class Edit {
        
    }

    
    @AfterAll
    public static void afterAll() {
        System.out.println("DESTROY");
    }
}