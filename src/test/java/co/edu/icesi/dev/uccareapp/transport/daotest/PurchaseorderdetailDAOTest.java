package co.edu.icesi.dev.uccareapp.transport.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderdetailDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@Rollback
public class PurchaseorderdetailDAOTest {

    @Autowired
    PurchaseorderdetailDAO purchaseorderdetailDAO;

    @BeforeAll
    public static void beforeAll() {
        
    }

    @Nested
    @DisplayName("Basic Cases")
    class Basic {
        @Test
        @Transactional(readOnly = true)
        public void count() {
            assertEquals(3, purchaseorderdetailDAO.count());
        }
        @Test
        @Transactional(rollbackFor = Exception.class)
        public void saveTest() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(0);
            purchaseorderdetailPK.setPurchaseorderdetailid(0);

            Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
            purchaseorderdetail.setId(purchaseorderdetailPK);
            purchaseorderdetail.setOrderqty(1);
            purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));

            purchaseorderdetailDAO.save(purchaseorderdetail);

            assertEquals(purchaseorderdetail, purchaseorderdetailDAO.findById(purchaseorderdetailPK).get());
        }

        @Test
        @Transactional(rollbackFor = Exception.class)
        public void updateTest() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(1);
            purchaseorderdetailPK.setPurchaseorderdetailid(1);

            Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
            purchaseorderdetail.setId(purchaseorderdetailPK);
            purchaseorderdetail.setOrderqty(100);
            purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));

            purchaseorderdetailDAO.update(purchaseorderdetail);

            assertEquals(purchaseorderdetail.getOrderqty(),
                purchaseorderdetailDAO.findById(purchaseorderdetailPK).get().getOrderqty());
        }

        @Test
        @Transactional(rollbackFor = Exception.class)
        public void deleteTest() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(1);
            purchaseorderdetailPK.setPurchaseorderdetailid(1);

            Purchaseorderdetail purchaseorderdetail = purchaseorderdetailDAO.findById(purchaseorderdetailPK).get();

            purchaseorderdetailDAO.delete(purchaseorderdetail);
        }

        @Test
        @Transactional(rollbackFor = Exception.class)
        public void deleteAllTest() {
            purchaseorderdetailDAO.deleteAll();

            assertEquals(0, purchaseorderdetailDAO.findAll().spliterator().estimateSize());
        }

        @Test
        @Transactional(readOnly = true)
        public void findByIdTest() {
            PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
            purchaseorderdetailPK.setPurchaseorderid(0);
            purchaseorderdetailPK.setPurchaseorderdetailid(0);

            Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
            purchaseorderdetail.setId(purchaseorderdetailPK);
            purchaseorderdetail.setOrderqty(100);
            purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));

            purchaseorderdetailDAO.save(purchaseorderdetail);

            assertEquals(purchaseorderdetail.getOrderqty(),
                    purchaseorderdetailDAO.findById(purchaseorderdetailPK).get().getOrderqty());
        }

        @Test
        @Transactional(readOnly = true)
        public void findAllTest() {
            assertEquals(3, purchaseorderdetailDAO.findAll().spliterator().estimateSize());
        }
    }

    @Nested
    @DisplayName("JPQL Cases")
    class JPQL {
        @Test
        @Transactional(readOnly = true)
        public void findByProductid() {
            long result = purchaseorderdetailDAO.findByProductid(1).spliterator().estimateSize();
            assertEquals(3, result);
        }

        @Test
        @Transactional(readOnly = true)
        public void findByUnitprice() {
            long result = purchaseorderdetailDAO.findByUnitprice(new BigDecimal("0.3")).spliterator().estimateSize();
            assertEquals(2, result);
        }
    }
}
