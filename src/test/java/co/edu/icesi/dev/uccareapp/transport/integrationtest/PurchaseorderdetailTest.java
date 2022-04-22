package co.edu.icesi.dev.uccareapp.transport.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.repository.PurchaseorderdetailRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.PurchaseorderheaderRepository;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailService;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseorderdetailTest { 
    @Autowired
    PurchaseorderdetailRepository purchaseorderdetailrepository;

    @Autowired
    PurchaseorderheaderRepository purchaseorderheaderrepository;

    @Autowired
    PurchaseorderdetailService purchaseorderdetailservice;

    @Autowired
    public PurchaseorderdetailTest(PurchaseorderdetailRepository purchaseorderdetailrepository, PurchaseorderheaderRepository purchaseorderheaderrepository) {
        this.purchaseorderdetailservice = new PurchaseorderdetailServiceImp(purchaseorderdetailrepository, purchaseorderheaderrepository);
    }

    @BeforeAll
    public void breforeAll() {
        System.out.println("--------->SETUP<---------");

        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(1);
        purchaseorderheaderrepository.save(purchaseorderheader);

        PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
        purchaseorderdetailPK.setPurchaseorderid(1);
        purchaseorderdetailPK.setPurchaseorderdetailid(1);
        
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(purchaseorderdetailPK);
        //purchaseorderdetail.setPurchaseorderheader(purchaseorderheaderrepository.findById(1).get());
        System.out.println(purchaseorderheaderrepository.findById(1).get().getPurchaseorderid());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetailrepository.save(purchaseorderdetail);
    }

    @AfterAll
    public void afterAll() {
        System.out.println("--------->DESTROY<---------");
        
        purchaseorderdetailrepository.deleteAll();
        purchaseorderheaderrepository.deleteAll();
    }

    @Nested
    @DisplayName("Save Cases")
    class Save {
        @BeforeEach
        public void beforeEach() {
            purchaseorderdetailrepository.deleteAll();
        }
        
        @Test
        @DisplayName("save purchaseorderdetail test 1")
        public void savePurchaseorderdetailtest1() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(1);
            purchaseorderdetailPK.setPurchaseorderdetailid(1);

            Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
            purchaseorderdetail.setId(purchaseorderdetailPK);
            purchaseorderdetail.setPurchaseorderheader(purchaseorderheaderrepository.findById(1).get());
            System.out.println(purchaseorderheaderrepository.findById(1).get().getPurchaseorderid());
            purchaseorderdetail.setOrderqty(1);
            purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
            
            assertTrue(purchaseorderdetailservice.savePurchaseorderdetail(purchaseorderdetail));
        }

        @Test
        @DisplayName("save purchaseorderdetail test 2")
        public void savePurchaseorderdetailtest2() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(1);
            purchaseorderdetailPK.setPurchaseorderdetailid(1);

            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(3);

            Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
            purchaseorderdetail.setId(purchaseorderdetailPK);
            purchaseorderdetail.setPurchaseorderheader(purchaseorderheader);
            purchaseorderdetail.setOrderqty(1);
            purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
            
            
            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                purchaseorderdetailservice.editPurchaseorderdetail(purchaseorderdetail);
            });
            assertEquals("Purchaseorderheader doesn't exist", thrown.getMessage());
        }

        @Test
        @DisplayName("save purchaseorderdetail test 3")
        public void savePurchaseorderdetailtest3() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(1);
            purchaseorderdetailPK.setPurchaseorderdetailid(1);

            Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
            purchaseorderdetail.setId(purchaseorderdetailPK);
            purchaseorderdetail.setPurchaseorderheader(purchaseorderheaderrepository.findById(1).get());
            purchaseorderdetail.setOrderqty(-1);
            purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
            
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderdetailservice.editPurchaseorderdetail(purchaseorderdetail);
            });
            assertEquals("orderqty must be greater than 0", thrown.getMessage());
        }
        @Test
        @DisplayName("save purchaseorderdetail test 4")
        public void savePurchaseorderdetailtest4() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(1);
            purchaseorderdetailPK.setPurchaseorderdetailid(1);

            Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
            purchaseorderdetail.setId(purchaseorderdetailPK);
            purchaseorderdetail.setPurchaseorderheader(purchaseorderheaderrepository.findById(1).get());
            purchaseorderdetail.setOrderqty(1);
            purchaseorderdetail.setUnitprice(new BigDecimal("-0.1"));
            
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderdetailservice.editPurchaseorderdetail(purchaseorderdetail);
            });
            assertEquals("unitprice must be greater than 0", thrown.getMessage());
        }
    }

    @Nested
    @DisplayName("Edit Cases")
    class Edit {
        
    }
}
