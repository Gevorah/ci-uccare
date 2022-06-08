package co.edu.icesi.dev.uccareapp.transport.delegatetest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.dev.uccareapp.transport.delegate.PurchaseorderdetailDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PurchaseorderdetailTest {
    @Mock
    private PurchaseorderdetailDelegate purchaseorderdetaildelegate; 

    @Test
    void savePurchaseorderdetail() throws Exception {
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(new PurchaseorderdetailPK());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);

        doReturn(purchaseorderdetail).when(purchaseorderdetaildelegate).savePurchaseorderdetail(any());

        assertEquals(purchaseorderdetail, purchaseorderdetaildelegate.savePurchaseorderdetail(purchaseorderdetail));
    }

    @Test void editPurchaseorderdetail() throws Exception {
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(new PurchaseorderdetailPK());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);

        doReturn(purchaseorderdetail).when(purchaseorderdetaildelegate).findById(anyInt(), anyInt());

        Integer update = 99;

        doAnswer(invocation -> {
            Purchaseorderdetail arg0 = invocation.getArgument(0);
            arg0.setOrderqty(update);
            return null;
        }).when(purchaseorderdetaildelegate).editPurchaseorderdetail(purchaseorderdetail);

        purchaseorderdetaildelegate.editPurchaseorderdetail(purchaseorderdetail);

        assertEquals(update, purchaseorderdetaildelegate.findById(0, 0).getOrderqty());
    }

    @Test
    void findById() throws Exception {
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(new PurchaseorderdetailPK());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);

        doReturn(purchaseorderdetail).when(purchaseorderdetaildelegate).findById(anyInt(), anyInt());

        assertEquals(purchaseorderdetail, purchaseorderdetaildelegate.findById(0, 0));
    }

    @Test
    void findAll() throws Exception {
        Purchaseorderdetail purchaseorderdetail1 = new Purchaseorderdetail();
        purchaseorderdetail1.setId(new PurchaseorderdetailPK());
        purchaseorderdetail1.setOrderqty(1);
        purchaseorderdetail1.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail1.setProductid(1);
        
        Purchaseorderdetail purchaseorderdetail2 = new Purchaseorderdetail();
        purchaseorderdetail2.setId(new PurchaseorderdetailPK());
        purchaseorderdetail2.setOrderqty(3);
        purchaseorderdetail2.setUnitprice(new BigDecimal("0.3"));
        purchaseorderdetail2.setProductid(3);

        Purchaseorderdetail purchaseorderdetail3 = new Purchaseorderdetail();
        purchaseorderdetail3.setId(new PurchaseorderdetailPK());
        purchaseorderdetail3.setOrderqty(6);
        purchaseorderdetail3.setUnitprice(new BigDecimal("0.6"));
        purchaseorderdetail3.setProductid(6);

        Purchaseorderdetail[] array = {purchaseorderdetail1, purchaseorderdetail2, purchaseorderdetail3};

        Iterable<Purchaseorderdetail> purchaseorderdetails = () -> new Iterator<Purchaseorderdetail>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Purchaseorderdetail next() { return array[index++]; }
        };

        doReturn(purchaseorderdetails).when(purchaseorderdetaildelegate).findAll();

        assertEquals(purchaseorderdetails, purchaseorderdetaildelegate.findAll());
        //assertEquals(3, purchaseorderdetaildelegate.findAll().spliterator().estimateSize());
    }

    @Test
    void deletePurchaseorderdetail() throws Exception {
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(new PurchaseorderdetailPK());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);

        doAnswer(invocation -> {
            return null;
        }).when(purchaseorderdetaildelegate).delete(purchaseorderdetail);

        purchaseorderdetaildelegate.delete(purchaseorderdetail);

        verify(purchaseorderdetaildelegate).delete(purchaseorderdetail);
        assertEquals(null, purchaseorderdetaildelegate.findById(0, 0));
    }
}