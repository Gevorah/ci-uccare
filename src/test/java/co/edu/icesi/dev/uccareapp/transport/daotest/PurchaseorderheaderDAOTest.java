package co.edu.icesi.dev.uccareapp.transport.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

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
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAOImp;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@Rollback
public class PurchaseorderheaderDAOTest {

    @Autowired
    PurchaseorderheaderDAOImp purchaseorderheaderDAO;

    @BeforeAll
    public static void beforeAll() {
    }

    @Nested
    @DisplayName("Basic Cases")
    class Basic {
        @Test
        @Transactional(readOnly = true)
        public void count() {
            assertEquals(3, purchaseorderheaderDAO.count());
        }

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
            // violate FOREIGN KEY or other constraints. 
            // purchaseorderheaderDAO.deleteAll();
            // assertEquals(0, purchaseorderheaderDAO.findAll().spliterator().estimateSize());
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
            long size = purchaseorderheaderDAO.findByShipmethodid(1).spliterator().estimateSize();
            assertEquals(2, size);
        }

        @Test
        @Transactional(readOnly = true)
        public void findByVendorid() {
            long size = purchaseorderheaderDAO.findByVendorid(1).spliterator().estimateSize();
            assertEquals(1, size);
        }

        @Test
        @Transactional(readOnly = true)
        public void findByDateRange() {
            LocalDate start = LocalDate.now().minusDays(1);
            LocalDate end = LocalDate.now().plusDays(1);
            
            long size = purchaseorderheaderDAO.findByDateRange(start, end).spliterator().estimateSize();

            assertEquals(2, size);
            
            Iterator<Object[]> result = purchaseorderheaderDAO.findByDateRange(start, end).iterator();

            Object[] hs = result.next();
            assertEquals(2, ((Purchaseorderheader) hs[0]).getPurchaseorderid());
            assertEquals(new BigDecimal("0.90"), hs[1]);

            hs = result.next();
            assertEquals(1, ((Purchaseorderheader) hs[0]).getPurchaseorderid());
            assertEquals(new BigDecimal("0.30"), hs[1]);
        }

        @Test
        @Transactional(readOnly = true)
        public void findByTwoDetails() {
            long size = purchaseorderheaderDAO.findByTwoDetails().spliterator().estimateSize();
            assertEquals(1, size);
        }
    }
}