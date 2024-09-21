package com.example.store.management.service;

import com.example.store.management.dto.CategoryDto;
import com.example.store.management.dto.ProductDto;
import com.example.store.management.entity.Category;
import com.example.store.management.entity.Product;
import com.example.store.management.mapper.CategoryMapper;
import com.example.store.management.mapper.ProductMapper;
import com.example.store.management.mapper.StoreMapper;
import com.example.store.management.repository.CategoryRepository;
import com.example.store.management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StoreMapper storeMapper;

    @Transactional
    public ProductDto createProduct(ProductDto productDto){
        if(productDto.getCategory()!=null){
            Category category=categoryMapper.convert(productDto.getCategory());
            //set child to parent first
            category.getProduct().add(productMapper.convert(productDto));
            //save parent
            categoryRepository.save(category);
        }
        //save child in db regardless
        return productMapper.convert(productRepository.save(productMapper.convert(productDto)));
    }

    public ProductDto deleteProduct(String productId){
        Product product=productRepository.findById(productId).get();
        ProductDto productDto=productMapper.convert(product);
        productRepository.delete(product);
        return productDto;
    }

    public ProductDto registerProduct(String productId, String categoryName){
        Product product=productRepository.findById(productId).get();
        Category category=categoryRepository.findByName(categoryName);

        //adding to parent first
        category.addProduct(product);
        //adding to child
        product.setCategory(category);
        //save parent first
        categoryRepository.save(category);
        //save child after
        productRepository.save(product);

        return productMapper.convert(product);
    }

    public ProductDto getProduct(String productId){
       return productMapper.convert( productRepository.findById(productId).get());
    }

    public ProductDto updateProduct(String productId,ProductDto productDto){
       Product product= productRepository.findById(productId).get();
       product.setDescription(productDto.getDescription());
       product.setName(productDto.getName());
       product.setPrice(productDto.getPrice());

      return productMapper.convert( productRepository.save(product));
    }
}
