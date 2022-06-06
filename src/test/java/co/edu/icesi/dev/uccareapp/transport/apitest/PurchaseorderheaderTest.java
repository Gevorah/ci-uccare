package co.edu.icesi.dev.uccareapp.transport.apitest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderheaderServiceImp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseorderheaderTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseorderheaderServiceImp purchaseorderheaderservice;

    @Test
    void savePurchaseorderheader() throws Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(post("/api/purchaseorderheaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(purchaseorderheader)))

            .andExpect(status().isOk());
    }

    @Test
    void editPurchaseorderheader() throws JsonProcessingException, Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        doReturn(Optional.of(purchaseorderheader)).when(purchaseorderheaderservice).findById(anyInt());

        mockMvc.perform(put("/api/purchaseorderheaders/{id}", 0)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(purchaseorderheader)))
            
            .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

        LocalDate localdate = LocalDate.now();
        List<Integer> date = List.of(localdate.getYear(), localdate.getMonthValue(), localdate.getDayOfMonth());

        doReturn(Optional.of(purchaseorderheader)).when(purchaseorderheaderservice).findById(anyInt());

        mockMvc.perform(get("/api/purchaseorderheaders/{id}", 0))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$.employeeid", is(0)))
            .andExpect(jsonPath("$.orderdate", is(date)))
            .andExpect(jsonPath("$.subtotal", is(0.1)));
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

        doReturn(purchaseorderheaders).when(purchaseorderheaderservice).findAll();

        mockMvc.perform(get("/api/purchaseorderheaders"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].subtotal", is(0.1)))
            .andExpect(jsonPath("$[1].subtotal", is(0.3)))
            .andExpect(jsonPath("$[2].subtotal", is(0.6)));
    }

    @Test
    void deletePurchaseorderheader() throws Exception {
        Purchaseorderheader purchaseorderheader = new Purchaseorderheader();
        purchaseorderheader.setPurchaseorderid(0);
        purchaseorderheader.setEmployeeid(0);
        purchaseorderheader.setOrderdate(LocalDate.now());
        purchaseorderheader.setSubtotal(new BigDecimal("0.1"));

        doReturn(Optional.of(purchaseorderheader)).when(purchaseorderheaderservice).findById(anyInt());

        mockMvc.perform(delete("/api/purchaseorderheaders/{id}", 0)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}