package com.example.store.management.controller;

import com.example.store.management.dto.CategoryDto;
import com.example.store.management.dto.ProductDto;
import com.example.store.management.entity.Category;
import com.example.store.management.entity.Product;
import com.example.store.management.service.CategoryService;
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
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;



@WebMvcTest(controllers = CategoryController.class)
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CategoryService categoryService;

    private Category category;
    private Product product;
    private ProductDto productDto;
    private CategoryDto categoryDto;
    private List<CategoryDto> categoryDtoList;
    private List<ProductDto> productDtoList;

    @BeforeEach
    public void init(){
        category=Category.builder().name("lactate").build();
        categoryDto=CategoryDto.builder().categoryId("1").name("lactate").build();
        product=Product.builder().price(100L).description("This product is a good yogurt").build();
        productDto=ProductDto.builder().price(100L).description("This product is a good yogurt").build();

        ProductDto productDto1=ProductDto.builder().price(100L).description("This product is a good cheese").build();
        ProductDto productDto2=ProductDto.builder().price(200L).description("This product is a good cheese2").build();
        productDtoList=Arrays.asList(productDto1,productDto2);
        categoryDto.setProduct(productDtoList);

    }


    @Test
    public void CategoryController_CreateCategory_ReturnCategoryDto() throws Exception {
            given(categoryService.saveCategory(ArgumentMatchers.any())).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        ResultActions response=mockMvc.perform(post("/categories/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)));


        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(categoryDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void CategoryController_GetProducts_ReturnOK()throws Exception{
        String categoryId="1";

        given(categoryService.getProducts(categoryId)).willReturn(productDtoList);

        ResultActions response=mockMvc.perform(get("/categories/getProducts/{id}",categoryId));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",CoreMatchers.is(productDtoList.size())))
                .andExpect(jsonPath("$[0].price",CoreMatchers.is(productDtoList.get(0).getPrice().intValue())))
                .andExpect(jsonPath("$[0].description",CoreMatchers.is(productDtoList.get(0).getDescription())))
                .andExpect(jsonPath("$[1].price",CoreMatchers.is(productDtoList.get(1).getPrice().intValue())))
                .andExpect(jsonPath("$[1].description",CoreMatchers.is(productDtoList.get(1).getDescription())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void CategoryController_UpdateCategory_ReturnCategoryDto()throws Exception{

        String categoryId = "1";
        String categoryName = "lactate";

        CategoryDto updatedCategoryDto = CategoryDto.builder()
                .categoryId(categoryId)
                .name(categoryName)
                .product(productDtoList)
                .build();

        given(categoryService.updateCategory(eq(categoryId), eq(categoryName))).willReturn(updatedCategoryDto);

        ResultActions response = mockMvc.perform(put("/categories/update/{categoryId}/{categoryName}", categoryId, categoryName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCategoryDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", CoreMatchers.is(updatedCategoryDto.getCategoryId())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(updatedCategoryDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }





}
