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

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;
import co.edu.icesi.dev.uccareapp.transport.service.PurchaseorderdetailService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseorderdetailTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseorderdetailService purchaseorderdetailservice;

    @Test
    void savePurchaseorderdetail() throws Exception {
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(new PurchaseorderdetailPK());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(post("/api/purchaseorderdetails")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(purchaseorderdetail)))

            .andExpect(status().isOk());
    }

    @Test
    void editPurchaseorderdetail() throws JsonProcessingException, Exception {
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(new PurchaseorderdetailPK());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        doReturn(Optional.of(purchaseorderdetail)).when(purchaseorderdetailservice).findById(anyInt(), anyInt());

        mockMvc.perform(put("/api/purchaseorderdetails/{headerid}-{detailid}", 0, 0)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(purchaseorderdetail)))
            
            .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        PurchaseorderdetailPK purchaseorderdetailPK = new PurchaseorderdetailPK();
        purchaseorderdetailPK.setPurchaseorderid(0);
        purchaseorderdetailPK.setPurchaseorderdetailid(0);

        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(purchaseorderdetailPK);
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);

        doReturn(Optional.of(purchaseorderdetail)).when(purchaseorderdetailservice).findById(anyInt(), anyInt());
        
        mockMvc.perform(get("/api/purchaseorderdetails/{headerid}-{detailid}", 0, 0))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$.orderqty", is(1)))
            .andExpect(jsonPath("$.unitprice", is(0.1)))
            .andExpect(jsonPath("$.productid", is(1)));
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

        doReturn(purchaseorderdetails).when(purchaseorderdetailservice).findAll();

        mockMvc.perform(get("/api/purchaseorderdetails"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].orderqty", is(1)))
            .andExpect(jsonPath("$[1].orderqty", is(3)))
            .andExpect(jsonPath("$[2].orderqty", is(6)));
    }

    @Test
    void deletePurchaseorderdetail() throws Exception {
        Purchaseorderdetail purchaseorderdetail = new Purchaseorderdetail();
        purchaseorderdetail.setId(new PurchaseorderdetailPK());
        purchaseorderdetail.setOrderqty(1);
        purchaseorderdetail.setUnitprice(new BigDecimal("0.1"));
        purchaseorderdetail.setProductid(1);

        doReturn(Optional.of(purchaseorderdetail)).when(purchaseorderdetailservice).findById(anyInt(), anyInt());

        mockMvc.perform(delete("/api/purchaseorderdetails/{headerid}-{detailid}", 0, 0)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}