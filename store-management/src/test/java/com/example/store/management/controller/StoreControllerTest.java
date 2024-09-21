package com.example.store.management.controller;

import com.example.store.management.dto.ProductDto;
import com.example.store.management.dto.StoreDto;
import com.example.store.management.entity.Product;
import com.example.store.management.entity.Store;
import com.example.store.management.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = StoreController.class)
public class StoreControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private StoreService storeService;

    private Store store;
    private Product product;
    private StoreDto storeDto;
    private ProductDto productDto;
    private List<StoreDto> storeDtoList;
    private List<ProductDto> productDtoList;

    @BeforeEach
    public void init(){
        store=Store.builder().name("Auchan").contactInfo("This is the contact info").build();
        product=Product.builder().price(100L).description("This product is a good yogurt").build();
        storeDto=StoreDto.builder().name("Auchan").contactInfo("This is the contact info").build();
        productDto=ProductDto.builder().price(100L).description("This product is a good yogurt").build();

        StoreDto storeDto1=StoreDto.builder().name("Auchan").contactInfo("This is the contact info").build();
        StoreDto storeDto2=StoreDto.builder().name("Auchan2").contactInfo("This is the contact info2").build();
        storeDtoList= Arrays.asList(storeDto1,storeDto2);

        ProductDto productDto1=ProductDto.builder().price(100L).description("This product is a good cheese").build();
        ProductDto productDto2=ProductDto.builder().price(200L).description("This product is a good cheese2").build();
        productDtoList=Arrays.asList(productDto1,productDto2);


    }

    @Test
    public void StoreController_CreateStore_ReturnCreated()throws Exception{
        given(storeService.saveStore(ArgumentMatchers.any())).willAnswer(invocationOnMock->invocationOnMock.getArgument(0));

        ResultActions response=mockMvc.perform(post("/stores/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(storeDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(storeDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo",CoreMatchers.is(storeDto.getContactInfo())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void StoreController_GetAllStore_ReturnStoreDto()throws Exception{
        given(storeService.getAllStores()).willReturn(storeDtoList);

        ResultActions response=mockMvc.perform(get("/stores/getStores")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",CoreMatchers.is(storeDtoList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",CoreMatchers.is(storeDtoList.get(0).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contactInfo",CoreMatchers.is(storeDtoList.get(0).getContactInfo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",CoreMatchers.is(storeDtoList.get(1).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].contactInfo",CoreMatchers.is(storeDtoList.get(1).getContactInfo())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void StoreController_DeleteStore_ReturnStoreDto() throws Exception {
        String storeId="1";

        given(storeService.deleteStore(anyString())).willReturn(storeDto);

        ResultActions response=mockMvc.perform(delete("/stores/delete/{id}",storeId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(storeDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo",CoreMatchers.is(storeDto.getContactInfo())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void StoreController_GetProducts_ReturnOK()throws Exception{
        String storeId="1";

        given(storeService.getProducts(storeId)).willReturn(productDtoList);

        ResultActions response=mockMvc.perform(get("/stores/getProducts/{idStore}",storeId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",CoreMatchers.is(productDtoList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price",CoreMatchers.is(productDtoList.get(0).getPrice().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description",CoreMatchers.is(productDtoList.get(0).getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price",CoreMatchers.is(productDtoList.get(1).getPrice().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description",CoreMatchers.is(productDtoList.get(1).getDescription())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void StoreController_DeleteProductFromStore_ReturnOK()throws Exception {
        {
            String storeId = "1";
            String productId = "101";

            given(storeService.deleteProduct(productId, storeId)).willReturn(productDto);

            ResultActions response = mockMvc.perform(delete("/stores/deleteProduct/{productId}/{idOfStore}", productId, storeId)
                    .contentType(MediaType.APPLICATION_JSON));

            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(productDto.getDescription())))
                    .andDo(MockMvcResultHandlers.print());
        }
    }

}
