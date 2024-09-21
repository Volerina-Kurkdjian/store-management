package com.example.store.management.service;


import com.example.store.management.dto.CategoryDto;
import com.example.store.management.entity.Category;
import com.example.store.management.entity.Product;
import com.example.store.management.mapper.CategoryMapper;
import com.example.store.management.mapper.ProductMapper;
import com.example.store.management.repository.CategoryRepository;
import com.example.store.management.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
