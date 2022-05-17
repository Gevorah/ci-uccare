package co.edu.icesi.dev.uccareapp.transport.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.VendorDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.service.VendorService;
import co.edu.icesi.dev.uccareapp.transport.service.VendorServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class VendorTest {
    
    @Mock
    private VendorDAO vendordao;
    
    @Mock
    private BusinessentityRepository businessentityrepository;

    @InjectMocks
    private VendorService vendorservice;

    @Autowired
    public VendorTest() {
        this.vendorservice = new VendorServiceImp();
    }

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
        @DisplayName("save vendor 1")
        public void saveVendorTest1(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(businessentity.getBusinessentityid());
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("https:");
            optvendor.get().setName("test");

            when(vendordao.findById(1)).thenReturn(optvendor);

            assertTrue(vendorservice.saveVendor(optvendor.get()));
            assertEquals(optvendor, vendordao.findById(1));
        }
        
        @Test
        @DisplayName("save vendor 2")
        public void saveVendorTest2(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(3);
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("https:");
            optvendor.get().setName("test");
            
            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                vendorservice.saveVendor(optvendor.get());
            });
            assertEquals("Businessentity doesn't exist", thrown.getMessage());
            
        }
        
        @Test
        @DisplayName("save vendor 3")
        public void saveVendorTest3(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(1);
            optvendor.get().setCreditrating(-1);
            optvendor.get().setPurchasingwebserviceurl("https:");
            optvendor.get().setName("test");

            when(vendordao.findById(1)).thenReturn(optvendor);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.saveVendor(optvendor.get());
            });
            assertEquals("creditrating must be greater than 0", thrown.getMessage());
        }
        @Test
        @DisplayName("save vendor 4")
        public void saveVendorTest4(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(1);
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("http:");
            optvendor.get().setName("test");

            when(vendordao.findById(1)).thenReturn(optvendor);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.saveVendor(optvendor.get());
            });
            assertEquals("URL must start with 'https'", thrown.getMessage());
        }
        @Test
        @DisplayName("save vendor 5")
        public void saveVendorTest5(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(1);
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("https");
            optvendor.get().setName(null);

            when(vendordao.findById(1)).thenReturn(optvendor);

            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                vendorservice.saveVendor(optvendor.get());
            });
            assertEquals("Fill the name field", thrown.getMessage());
        }
    }
    
    @Nested
    @DisplayName("Edit Cases")
    class Edit {
        @Test
        @DisplayName("edit vendor 1")
        public void editVendorTest1(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(businessentity.getBusinessentityid());
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("https:");
            optvendor.get().setName("test");
            
            when(vendordao.existsById(1)).thenReturn(true);
            when(vendordao.findById(1)).thenReturn(optvendor);

            assertTrue(vendorservice.editVendor(optvendor.get()));
            assertEquals(optvendor, vendordao.findById(1));
        }
        
        @Test
        @DisplayName("edit vendor 2")
        public void editVendorTest2(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(3);
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("https:");
            optvendor.get().setName("test");
            
            when(vendordao.existsById(1)).thenReturn(true);
            when(vendordao.findById(1)).thenReturn(optvendor);
            
            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                vendorservice.editVendor(optvendor.get());
            });
            assertEquals("Vendor is null or doesn't exist", thrown.getMessage());
            
        }
        
        @Test
        @DisplayName("edit vendor 3")
        public void editVendorTest3(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(1);
            optvendor.get().setCreditrating(-1);
            optvendor.get().setPurchasingwebserviceurl("https:");
            optvendor.get().setName("test");
            
            when(vendordao.existsById(1)).thenReturn(true);
            when(vendordao.findById(1)).thenReturn(optvendor);
            
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.editVendor(optvendor.get());
            });
            assertEquals("creditrating must be greater than 0", thrown.getMessage());
        }
        @Test
        @DisplayName("edit vendor 4")
        public void editVendorTest4(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(1);
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("http:");
            optvendor.get().setName("test");
            
            when(vendordao.existsById(1)).thenReturn(true);
            when(vendordao.findById(1)).thenReturn(optvendor);
            
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                vendorservice.editVendor(optvendor.get());
            });
            assertEquals("URL must start with 'https'", thrown.getMessage());
        }
        @Test
        @DisplayName("edit vendor 5")
        public void editVendorTest5(){
            Businessentity businessentity = new Businessentity();
            businessentity.setBusinessentityid(1);

            when(businessentityrepository.existsById(1)).thenReturn(true);

            Optional<Vendor> optvendor = Optional.of(new Vendor());
            optvendor.get().setBusinessentityid(1);
            optvendor.get().setCreditrating(1);
            optvendor.get().setPurchasingwebserviceurl("https");
            optvendor.get().setName(null);
            
            when(vendordao.existsById(1)).thenReturn(true);
            when(vendordao.findById(1)).thenReturn(optvendor);
            
            NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
                vendorservice.editVendor(optvendor.get());
            });
            assertEquals("Fill the name field", thrown.getMessage());
        }
    }
}
