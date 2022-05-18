package co.edu.icesi.dev.uccareapp.transport.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.ShipmethodDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@Rollback
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShipmethodDAOTest {

    @Autowired
    ShipmethodDAO shipmethodDAO;

    @BeforeAll
    public void beforeAll() {
        assertNotNull(shipmethodDAO);

        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(1);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("FOUR");

        shipmethodDAO.save(shipmethod);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void saveTest() {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("FOUR");

        shipmethodDAO.save(shipmethod);

        assertEquals(shipmethod, shipmethodDAO.findById(0).get());
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void updateTest() {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(1);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Elephant");

        shipmethodDAO.update(shipmethod);

        assertEquals(shipmethod.getName(), shipmethodDAO.findById(1).get().getName());
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void deleteTest() {
        Shipmethod shipmethod = shipmethodDAO.findById(1).get();

        shipmethodDAO.delete(shipmethod);

        assertTrue(shipmethodDAO.findById(1).isEmpty());
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllTest() {
        shipmethodDAO.deleteAll();
        
        assertEquals(0, shipmethodDAO.findAll().spliterator().estimateSize());
    }

    @Test
    @Transactional(readOnly = true)
    public void findByIdTest() {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Elephant");

        shipmethodDAO.save(shipmethod);

        assertEquals(shipmethod.getName(), shipmethodDAO.findById(0).get().getName());
    }

    @Test
    @Transactional(readOnly = true)
    public void findAllTest() {
        assertEquals(1, shipmethodDAO.findAll().spliterator().estimateSize());
    }
}