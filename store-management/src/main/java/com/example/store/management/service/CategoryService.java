package com.example.store.management.service;


import com.example.store.management.dto.CategoryDto;
import com.example.store.management.dto.ProductDto;
import com.example.store.management.entity.Category;
import com.example.store.management.entity.Product;
import com.example.store.management.mapper.CategoryMapper;
import com.example.store.management.mapper.ProductMapper;
import com.example.store.management.repository.CategoryRepository;
import com.example.store.management.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    public CategoryDto saveCategory(CategoryDto categoryDto){
        Category category=categoryMapper.convert(categoryDto);
        //first saving parent
        categoryRepository.save(category);

        //saving product and first setting the category to it
        for(Product product:category.getProduct()){
            product.setCategory(category);
            productRepository.save(product);
        }
        return categoryMapper.convert(category);
    }

    public List<ProductDto> getProducts(String categoryId){
        Category category=categoryRepository.findById(categoryId).get();
        List<ProductDto> productDtos=new ArrayList<>();

        for(Product product:category.getProduct()){
            productDtos.add(productMapper.convert(product));
        }
        return productDtos;
    }

}
