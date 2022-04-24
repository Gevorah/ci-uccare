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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.repository.ShipmethodRepository;
import co.edu.icesi.dev.uccareapp.transport.service.ShipmethodService;
import co.edu.icesi.dev.uccareapp.transport.service.ShipmethodServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class ShipmethodTest {
    
    @Mock
    private ShipmethodRepository shipmethodrepository;
    
    @InjectMocks
    private ShipmethodService shipmethodservice;

    @Autowired
    public ShipmethodTest() {
        this.shipmethodservice = new ShipmethodServiceImp(shipmethodrepository);
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
        @DisplayName("save shipmethod 1")
        public void saveShipmethodTest1(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("0.1"));
            optshipmethod.get().setName("FOUR");

            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            assertTrue(shipmethodservice.saveShipmethod(optshipmethod.get()));
            assertEquals(optshipmethod, shipmethodrepository.findById(1));
        }

        
        @Test
        @DisplayName("save shipmethod 2")
        public void saveShipmethodTest2(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("-0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("0.1"));
            optshipmethod.get().setName("FOUR");

            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.saveShipmethod(optshipmethod.get());
            });
            assertEquals("shipbase must be greater than 0", thrown.getMessage());
        }

        
        @Test
        @DisplayName("save shipmethod 3")
        public void saveShipmethodTest3(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("-0.1"));
            optshipmethod.get().setName("FOUR");

            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.saveShipmethod(optshipmethod.get());
            });
            assertEquals("shiprate must be greater than 0", thrown.getMessage());
        }

        
        @Test
        @DisplayName("save shipmethod 4")
        public void saveShipmethodTest4(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("0.1"));
            optshipmethod.get().setName("FOU");

            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.saveShipmethod(optshipmethod.get());
            });
            assertEquals("name must have at least 4 chars", thrown.getMessage());
        }
    }

    @Nested
    @DisplayName("Edit Cases")
    class Edit {
        @Test
        @DisplayName("edit shipmethod 1")
        public void editShipmethodTest1(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("0.1"));
            optshipmethod.get().setName("FOUR");
            
            when(shipmethodrepository.existsById(1)).thenReturn(true);
            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            assertTrue(shipmethodservice.editShipmethod(optshipmethod.get()));
            assertEquals(optshipmethod, shipmethodrepository.findById(1));
        }

        
        @Test
        @DisplayName("edit shipmethod 2")
        public void editShipmethodTest2(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("-0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("0.1"));
            optshipmethod.get().setName("FOUR");
            
            when(shipmethodrepository.existsById(1)).thenReturn(true);
            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.editShipmethod(optshipmethod.get());
            });
            assertEquals("shipbase must be greater than 0", thrown.getMessage());
        }

        
        @Test
        @DisplayName("edit shipmethod 3")
        public void editShipmethodTest3(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("-0.1"));
            optshipmethod.get().setName("FOUR");

            when(shipmethodrepository.existsById(1)).thenReturn(true);
            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.editShipmethod(optshipmethod.get());
            });
            assertEquals("shiprate must be greater than 0", thrown.getMessage());
        }

        
        @Test
        @DisplayName("edit shipmethod 4")
        public void editShipmethodTest4(){
        	Optional<Shipmethod> optshipmethod = Optional.of(new Shipmethod());
            optshipmethod.get().setShipmethodid(1);
            optshipmethod.get().setShipbase(new BigDecimal("0.1"));
            optshipmethod.get().setShiprate(new BigDecimal("0.1"));
            optshipmethod.get().setName("FOU");

            when(shipmethodrepository.existsById(1)).thenReturn(true);
            when(shipmethodrepository.findById(1)).thenReturn(optshipmethod);

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.editShipmethod(optshipmethod.get());
            });
            assertEquals("name must have at least 4 chars", thrown.getMessage());
        }
    }
}
