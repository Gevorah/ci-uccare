package co.edu.icesi.dev.uccareapp.transport.delegatetest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.dev.uccareapp.transport.delegate.VendorDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VendorTest {
    @Mock
    private VendorDelegate vendordelegate; 

    @Test
    void saveVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");

        doReturn(vendor).when(vendordelegate).saveVendor(any());

        assertEquals(vendor, vendordelegate.saveVendor(vendor));
    }

    @Test void editVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");

        doReturn(vendor).when(vendordelegate).findById(anyInt());

        String update = "Pandorah";

        doAnswer(invocation -> {
            Vendor arg0 = invocation.getArgument(0);
            arg0.setName(update);
            return null;
        }).when(vendordelegate).editVendor(vendor);

        vendordelegate.editVendor(vendor);

        assertEquals(update, vendordelegate.findById(0).getName());
    }

    @Test
    void findById() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");

        doReturn(vendor).when(vendordelegate).findById(anyInt());

        assertEquals(vendor, vendordelegate.findById(0));
    }

    @Test
    void findAll() throws Exception {
        Vendor vendor1 = new Vendor();
        vendor1.setBusinessentityid(0);
        vendor1.setName("Nate");
        vendor1.setCreditrating(10);
        
        Vendor vendor2 = new Vendor();
        vendor2.setBusinessentityid(1);
        vendor2.setName("Pandorah");
        vendor2.setCreditrating(60);

        Vendor vendor3 = new Vendor();
        vendor3.setBusinessentityid(2);
        vendor3.setName("Nox");
        vendor3.setCreditrating(30);

        Vendor[] array = {vendor1, vendor2, vendor3};

        Iterable<Vendor> vendors = () -> new Iterator<Vendor>() {
            private int index = 0;
            @Override public boolean hasNext() { return array.length > index; }
            @Override public Vendor next() { return array[index++]; }
        };

        doReturn(vendors).when(vendordelegate).findAll();

        assertEquals(vendors, vendordelegate.findAll());
        assertEquals(3, vendordelegate.findAll().spliterator().estimateSize());
    }

    @Test
    void deleteVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");

        doAnswer(invocation -> {
            return null;
        }).when(vendordelegate).delete(vendor);

        vendordelegate.delete(vendor);

        verify(vendordelegate).delete(vendor);
        assertEquals(null, vendordelegate.findById(0));
    }
}