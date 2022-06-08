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

import co.edu.icesi.dev.uccareapp.transport.delegate.ShipmethodDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ShipmethodTest {
    @Mock
    private ShipmethodDelegate shipmethoddelegate; 

    @Test
    void saveShipmethod() throws Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");

        doReturn(shipmethod).when(shipmethoddelegate).saveShipmethod(any());

        assertEquals(shipmethod, shipmethoddelegate.saveShipmethod(shipmethod));
    }

    @Test void editShipmethod() throws Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");

        doReturn(shipmethod).when(shipmethoddelegate).findById(anyInt());

        String update = "AirS";

        doAnswer(invocation -> {
            Shipmethod arg0 = invocation.getArgument(0);
            arg0.setName(update);
            return null;
        }).when(shipmethoddelegate).editShipmethod(shipmethod);

        shipmethoddelegate.editShipmethod(shipmethod);

        assertEquals(update, shipmethoddelegate.findById(0).getName());
    }

    @Test
    void findById() throws Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");

        doReturn(shipmethod).when(shipmethoddelegate).findById(anyInt());

        assertEquals(shipmethod, shipmethoddelegate.findById(0));
    }

    @Test
    void findAll() throws Exception {
        Shipmethod shipmethod1 = new Shipmethod();
        shipmethod1.setShipmethodid(0);
        shipmethod1.setShipbase(new BigDecimal("0.1"));
        shipmethod1.setShiprate(new BigDecimal("0.1"));
        shipmethod1.setName("Ocean");

        
        Shipmethod shipmethod2 = new Shipmethod();
        shipmethod2.setShipmethodid(1);
        shipmethod2.setShipbase(new BigDecimal("0.3"));
        shipmethod2.setShiprate(new BigDecimal("0.3"));
        shipmethod2.setName("AirS");


        Shipmethod shipmethod3 = new Shipmethod();
        shipmethod3.setShipmethodid(2);
        shipmethod3.setShipbase(new BigDecimal("0.6"));
        shipmethod3.setShiprate(new BigDecimal("0.6"));
        shipmethod3.setName("Train");

        Shipmethod[] array = {shipmethod1, shipmethod2, shipmethod3};

        Iterable<Shipmethod> shipmethods = () -> new Iterator<Shipmethod>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Shipmethod next() { return array[index++]; }
        };

        doReturn(shipmethods).when(shipmethoddelegate).findAll();

        assertEquals(shipmethods, shipmethoddelegate.findAll());
        //assertEquals(3, shipmethoddelegate.findAll().spliterator().estimateSize());
    }

    @Test
    void deleteShipmethod() throws Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");

        doAnswer(invocation -> {
            return null;
        }).when(shipmethoddelegate).delete(shipmethod);

        shipmethoddelegate.delete(shipmethod);

        verify(shipmethoddelegate).delete(shipmethod);
        assertEquals(null, shipmethoddelegate.findById(0));
    }
}