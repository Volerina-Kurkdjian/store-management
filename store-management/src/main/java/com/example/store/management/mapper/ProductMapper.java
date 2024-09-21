package com.example.store.management.mapper;


import com.example.store.management.dto.ProductDto;
import com.example.store.management.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    ModelMapper modelMapper;

    public ProductDto convert(Product product){
        ProductDto productDto=modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public Product convert(ProductDto productDto){
        Product product=modelMapper.map(productDto, Product.class);
        return product;
    }
}
