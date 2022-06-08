package co.edu.icesi.dev.uccareapp.transport.delegatetest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.dev.uccareapp.transport.delegate.PurchaseorderheaderDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PurchaseorderheaderTest {
    @Mock
    private PurchaseorderheaderDelegate purchaseorderheaderdelegate; 

    @Test
    void savePurchaseorderheader() throws Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

        doReturn(purchaseorderheader).when(purchaseorderheaderdelegate).savePurchaseorderheader(any());

        assertEquals(purchaseorderheader, purchaseorderheaderdelegate.savePurchaseorderheader(purchaseorderheader));
    }

    @Test void editPurchaseorderheader() throws Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

        doReturn(purchaseorderheader).when(purchaseorderheaderdelegate).findById(anyInt());

        LocalDate update = LocalDate.now().minusDays(3);

        doAnswer(invocation -> {
            Purchaseorderheader arg0 = invocation.getArgument(0);
            arg0.setOrderdate(update);
            return null;
        }).when(purchaseorderheaderdelegate).editPurchaseorderheader(purchaseorderheader);

        purchaseorderheaderdelegate.editPurchaseorderheader(purchaseorderheader);

        assertEquals(update, purchaseorderheaderdelegate.findById(0).getOrderdate());
    }

    @Test
    void findById() throws Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

        doReturn(purchaseorderheader).when(purchaseorderheaderdelegate).findById(anyInt());

        assertEquals(purchaseorderheader, purchaseorderheaderdelegate.findById(0));
    }

    @Test
    void findAll() throws Exception {
        Purchaseorderheader purchaseorderheader1 = new Purchaseorderheader();
        purchaseorderheader1.setPurchaseorderid(0);
        purchaseorderheader1.setEmployeeid(0);
        purchaseorderheader1.setOrderdate(LocalDate.now());
        purchaseorderheader1.setSubtotal(new BigDecimal("0.1"));
        
        Purchaseorderheader purchaseorderheader2 = new Purchaseorderheader();
        purchaseorderheader2.setPurchaseorderid(1);
        purchaseorderheader2.setEmployeeid(1);
        purchaseorderheader2.setOrderdate(LocalDate.now().minusDays(1));
        purchaseorderheader2.setSubtotal(new BigDecimal("0.3"));

        Purchaseorderheader purchaseorderheader3 = new Purchaseorderheader();
        purchaseorderheader3.setPurchaseorderid(2);
        purchaseorderheader3.setEmployeeid(2);
        purchaseorderheader3.setOrderdate(LocalDate.now().minusDays(2));
        purchaseorderheader3.setSubtotal(new BigDecimal("0.6"));

        Purchaseorderheader[] array = {purchaseorderheader1, purchaseorderheader2, purchaseorderheader3};

        Iterable<Purchaseorderheader> purchaseorderheaders = () -> new Iterator<Purchaseorderheader>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Purchaseorderheader next() { return array[index++]; }
        };

        doReturn(purchaseorderheaders).when(purchaseorderheaderdelegate).findAll();

        assertEquals(purchaseorderheaders, purchaseorderheaderdelegate.findAll());
        //assertEquals(3, purchaseorderheaderdelegate.findAll().spliterator().estimateSize());
    }

    @Test
    void deletePurchaseorderheader() throws Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

        doAnswer(invocation -> {
            return null;
        }).when(purchaseorderheaderdelegate).delete(purchaseorderheader);

        purchaseorderheaderdelegate.delete(purchaseorderheader);

        verify(purchaseorderheaderdelegate).delete(purchaseorderheader);
        assertEquals(null, purchaseorderheaderdelegate.findById(0));
    }
}