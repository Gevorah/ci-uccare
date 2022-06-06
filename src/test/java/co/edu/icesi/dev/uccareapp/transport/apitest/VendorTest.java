package co.edu.icesi.dev.uccareapp.transport.apitest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.service.VendorService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VendorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorService vendorservice;

    @Test
    void saveVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/vendors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(vendor)))

            .andExpect(status().isOk());
    }

    @Test
    void editVendor() throws JsonProcessingException, Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");
        
        ObjectMapper objectMapper = new ObjectMapper();

        doReturn(Optional.of(vendor)).when(vendorservice).findById(anyInt());

        mockMvc.perform(put("/api/vendors/{id}", 0)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(vendor)))
            
            .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");

        doReturn(Optional.of(vendor)).when(vendorservice).findById(anyInt());

        mockMvc.perform(get("/api/vendors/{id}", 0))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$.name", is("Nate")))
            .andExpect(jsonPath("$.creditrating", is(10)))
            .andExpect(jsonPath("$.purchasingwebserviceurl", is("https:")));
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

        doReturn(vendors).when(vendorservice).findAll();

        mockMvc.perform(get("/api/vendors"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is("Nate")))
            .andExpect(jsonPath("$[1].name", is("Pandorah")))
            .andExpect(jsonPath("$[2].name", is("Nox")));
    }

    @Test
    void deleteVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setBusinessentityid(0);
        vendor.setName("Nate");
        vendor.setCreditrating(10);
        vendor.setPurchasingwebserviceurl("https:");

        doReturn(Optional.of(vendor)).when(vendorservice).findById(anyInt());

        mockMvc.perform(delete("/api/vendors/{id}", 0)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}