package co.edu.icesi.dev.uccareapp.transport.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderdetailDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailService;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class PurchaseorderdetailTest {
    
    @Mock
    private PurchaseorderdetailDAO purchaseorderdetaildao;
    
    @Mock
    private PurchaseorderheaderDAO purchaseorderheaderdao;

    @InjectMocks
    private PurchaseorderdetailService purchaseorderdetailservice;

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
        @DisplayName("save purchaseorderdetail 1")
        public void savePurchaseorderdetailTest1(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);

            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("0.1"));

            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            assertTrue(purchaseorderdetailservice.savePurchaseorderdetail(optpurchaseorderdetail.get()));
            assertEquals(optpurchaseorderdetail, purchaseorderdetaildao.findById(purchaseorderdetailPK));
        }
        
        @Test
        @DisplayName("save purchaseorderdetail 2")
        public void savePurchaseorderdetailTest2(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(3);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);

            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("0.1"));

            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                purchaseorderdetailservice.savePurchaseorderdetail(optpurchaseorderdetail.get());
            });
            assertEquals("Purchaseorderheader doesn't exist", thrown.getMessage());
        }
        
        @Test
        @DisplayName("save purchaseorderdetail 3")
        public void savePurchaseorderdetailTest3(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);

            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(-1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("0.1"));

            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderdetailservice.savePurchaseorderdetail(optpurchaseorderdetail.get());
            });
            assertEquals("orderqty must be greater than 0", thrown.getMessage());
        }
        
        @Test
        @DisplayName("save purchaseorderdetail 4")
        public void savePurchaseorderdetailTest4(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);

            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("-0.1"));

            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderdetailservice.savePurchaseorderdetail(optpurchaseorderdetail.get());
            });
            assertEquals("unitprice must be greater than 0", thrown.getMessage());
        }
    }
    
    @Nested
    @DisplayName("Edit Cases")
    class Edit {
        @Test
        @DisplayName("edit purchaseorderdetail 1")
        public void editPurchaseorderdetailTest1(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(Optional.of(purchaseorderheader));

            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("0.1"));

            when(purchaseorderdetaildao.existsById(purchaseorderdetailPK)).thenReturn(true);
            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            assertTrue(purchaseorderdetailservice.editPurchaseorderdetail(optpurchaseorderdetail.get()));
            assertEquals(optpurchaseorderdetail, purchaseorderdetaildao.findById(purchaseorderdetailPK));
        }
        
        @Test
        @DisplayName("edit purchaseorderdetail 2")
        public void editPurchaseorderdetailTest2(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(3);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(Optional.of(purchaseorderheader));


            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("0.1"));

            when(purchaseorderdetaildao.existsById(purchaseorderdetailPK)).thenReturn(true);
            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                purchaseorderdetailservice.editPurchaseorderdetail(optpurchaseorderdetail.get());
            });
            assertEquals("Purchaseorderheader doesn't exist", thrown.getMessage());
        }
        
        @Test
        @DisplayName("edit purchaseorderdetail 3")
        public void editPurchaseorderdetailTest3(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(Optional.of(purchaseorderheader));


            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(-1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("0.1"));

            when(purchaseorderdetaildao.existsById(purchaseorderdetailPK)).thenReturn(true);
            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderdetailservice.editPurchaseorderdetail(optpurchaseorderdetail.get());
            });
            assertEquals("orderqty must be greater than 0", thrown.getMessage());
        }
        
        @Test
        @DisplayName("edit purchaseorderdetail 4")
        public void editPurchaseorderdetailTest4(){
            Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
            purchaseorderheader.setPurchaseorderid(1);

            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderheaderdao.findById(1)).thenReturn(Optional.of(purchaseorderheader));


            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderdetailid(1);
            purchaseorderdetailPK.setPurchaseorderid(purchaseorderheader.getPurchaseorderid());

            Optional<Purchaseorderdetail> optpurchaseorderdetail = Optional.of(new Purchaseorderdetail());
            optpurchaseorderdetail.get().setId(purchaseorderdetailPK);
            optpurchaseorderdetail.get().setPurchaseorderheader(purchaseorderheader);
            optpurchaseorderdetail.get().setOrderqty(1);
            optpurchaseorderdetail.get().setUnitprice(new BigDecimal("-0.1"));

            when(purchaseorderdetaildao.existsById(purchaseorderdetailPK)).thenReturn(true);
            when(purchaseorderheaderdao.existsById(1)).thenReturn(true);
            when(purchaseorderdetaildao.findById(purchaseorderdetailPK)).thenReturn(optpurchaseorderdetail);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                purchaseorderdetailservice.editPurchaseorderdetail(optpurchaseorderdetail.get());
            });
            assertEquals("unitprice must be greater than 0", thrown.getMessage());
        }
    }
}
