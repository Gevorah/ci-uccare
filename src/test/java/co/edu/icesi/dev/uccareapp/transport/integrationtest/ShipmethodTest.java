package co.edu.icesi.dev.uccareapp.transport.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Iterator;

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
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.repository.ShipmethodRepository;
import co.edu.icesi.dev.uccareapp.transport.service.ShipmethodService;
import co.edu.icesi.dev.uccareapp.transport.service.ShipmethodServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShipmethodTest {
    @Autowired
    private ShipmethodRepository shipmethodrepository;

    @Autowired
    private ShipmethodService shipmethodservice;

    @Autowired
    public ShipmethodTest() {
        this.shipmethodservice = new ShipmethodServiceImp(shipmethodrepository);
    }

    @BeforeAll
    public void beforeAll() {
        System.out.println("--------->SETUP<---------");
        
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(1);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("FOUR");
        shipmethodrepository.save(shipmethod);
    }

    @AfterAll
    public void afterAll() {
        System.out.println("--------->DESTROY<---------");

        shipmethodrepository.deleteAll();        
    }

    @Nested
    @DisplayName("Save Cases")
    public class Save {
        @BeforeEach
        public void beforeEach() {
            shipmethodrepository.deleteAll();
        }

        @Test
        @DisplayName("save shipmethod test 1")
        public void saveShipmethodTest1() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("0.1"));
            shipmethod.setShiprate(new BigDecimal("0.1"));
            shipmethod.setName("FOUR");

            assertTrue(shipmethodservice.saveShipmethod(shipmethod));
        }

        @Test
        @DisplayName("save shipmethod test 2")
        public void saveShipmethodTest2() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("-0.1"));
            shipmethod.setShiprate(new BigDecimal("0.1"));
            shipmethod.setName("FOUR");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.saveShipmethod(shipmethod);
            });
            assertEquals("shipbase must be greater than 0", thrown.getMessage());
        }

        @Test
        @DisplayName("save shipmethod test 3")
        public void saveShipmethodTest3() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("0.1"));
            shipmethod.setShiprate(new BigDecimal("-0.1"));
            shipmethod.setName("FOUR");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.saveShipmethod(shipmethod);
            });
            assertEquals("shiprate must be greater than 0", thrown.getMessage());
        }
        
        @Test
        @DisplayName("save shipmethod test 4")
        public void saveShipmethodTest4() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("0.1"));
            shipmethod.setShiprate(new BigDecimal("0.1"));
            shipmethod.setName("FOU");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.saveShipmethod(shipmethod);
            });
            assertEquals("name must have at least 4 chars", thrown.getMessage());
        }
    }

    @Nested
    @DisplayName("Edit Cases")
    public class Edit {
        @Test
        @DisplayName("edit shipmethod test 1")
        public void editShipmethodTest1() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("0.1"));
            shipmethod.setShiprate(new BigDecimal("0.1"));
            shipmethod.setName("FOUR");

            assertTrue(shipmethodservice.editShipmethod(shipmethod));
        }

        @Test
        @DisplayName("edit shipmethod test 2")
        public void editShipmethodTest2() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("-0.1"));
            shipmethod.setShiprate(new BigDecimal("0.1"));
            shipmethod.setName("FOUR");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.editShipmethod(shipmethod);
            });
            assertEquals("shipbase must be greater than 0", thrown.getMessage());
        }

        @Test
        @DisplayName("edit shipmethod test 3")
        public void editShipmethodTest3() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("0.1"));
            shipmethod.setShiprate(new BigDecimal("-0.1"));
            shipmethod.setName("FOUR");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.editShipmethod(shipmethod);
            });
            assertEquals("shiprate must be greater than 0", thrown.getMessage());
        }
        
        @Test
        @DisplayName("edit shipmethod test 4")
        public void editShipmethodTest4() {
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("0.1"));
            shipmethod.setShiprate(new BigDecimal("0.1"));
            shipmethod.setName("FOU");

            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                shipmethodservice.editShipmethod(shipmethod);
            });
            assertEquals("name must have at least 4 chars", thrown.getMessage());
        }
    }
}