package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jazva.challenge.api.ProductInventoryApi;
import com.jazva.challenge.model.ProductInventoryRequest;
import com.jazva.challenge.service.ProductInventoryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductInventoryApiTest {

    private static final String URL = "/api/products";
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private ProductInventoryService productInventoryService;

    @InjectMocks
    private ProductInventoryApi productInventoryApi;

    @Before
    public void setUp(){
        mockMvc = standaloneSetup(productInventoryApi).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getQuantitiesByLocationTest() throws Exception {
        String productId1 = "tr1";
        String productId2 = "tr2";

        mockMvc.perform(get(URL + "/{productId}/quantities", productId1))
                .andExpect(status().isOk());

        mockMvc.perform(get(URL + "/{productId}/quantities", productId2))
                .andExpect(status().isOk());

        verify(productInventoryService).getQuantitiesByLocation(productId1);
        verify(productInventoryService).getQuantitiesByLocation(productId2);
    }

    @Test
    public void addInventoryTest() throws Exception {
        ProductInventoryRequest productInventoryRequest = new ProductInventoryRequest();
        productInventoryRequest.setProductId("tr1");
        productInventoryRequest.setLocationId(2);
        productInventoryRequest.setQuantity(5);

        String jsonData = objectMapper.writeValueAsString(productInventoryRequest);

        mockMvc.perform(put(URL + "/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());

        verify(productInventoryService).addInventory(productInventoryRequest);
    }

    @Test
    public void resetInventoriesTest() throws Exception {
        String productId = "tr1";

        mockMvc.perform(put(URL + "/{productId}/inventory/reset", productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productInventoryService).resetInventories(productId);
    }

    @Test
    public void getTotalinventoryTest() throws Exception {
        String productId = "tr1";

        mockMvc.perform(get(URL + "/{productId}/inventory/total", productId))
                .andExpect(status().isOk());

        verify(productInventoryService).getTotalInventory(productId);
    }
}
