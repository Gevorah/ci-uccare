package co.edu.icesi.dev.uccareapp.transport.apitest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.service.ShipmethodService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ShipmethodTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmethodService shipmethodservice;

    @Test
    void saveShipmethod() throws Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/shipmethods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(shipmethod)))

            .andExpect(status().isOk());
    }

    @Test
    void editShipmethod() throws JsonProcessingException, Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");
        
        ObjectMapper objectMapper = new ObjectMapper();

        doReturn(Optional.of(shipmethod)).when(shipmethodservice).findById(anyInt());

        mockMvc.perform(put("/api/shipmethods/{id}", 0)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(shipmethod)))
            
            .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");

        doReturn(Optional.of(shipmethod)).when(shipmethodservice).findById(anyInt());

        mockMvc.perform(get("/api/shipmethods/{id}", 0))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$.shipbase", is(0.1)))
            .andExpect(jsonPath("$.shiprate", is(0.1)))
            .andExpect(jsonPath("$.name", is("Ocean")));
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

        doReturn(shipmethods).when(shipmethodservice).findAll();

        mockMvc.perform(get("/api/shipmethods"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is("Ocean")))
            .andExpect(jsonPath("$[1].name", is("AirS")))
            .andExpect(jsonPath("$[2].name", is("Train")));
    }

    @Test
    void deleteShipmethod() throws Exception {
        Shipmethod shipmethod = new Shipmethod();
        shipmethod.setShipmethodid(0);
        shipmethod.setShipbase(new BigDecimal("0.1"));
        shipmethod.setShiprate(new BigDecimal("0.1"));
        shipmethod.setName("Ocean");

        doReturn(Optional.of(shipmethod)).when(shipmethodservice).findById(anyInt());

        mockMvc.perform(delete("/api/shipmethods/{id}", 0)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}