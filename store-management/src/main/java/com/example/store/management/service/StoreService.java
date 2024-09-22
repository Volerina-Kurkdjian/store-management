package com.example.store.management.service;


import com.example.store.management.dto.ProductDto;
import com.example.store.management.dto.StoreDto;
import com.example.store.management.entity.Product;
import com.example.store.management.entity.Store;
import com.example.store.management.exception.ProductNotFoundException;
import com.example.store.management.exception.StoreNotFoundException;
import com.example.store.management.mapper.ProductMapper;
import com.example.store.management.mapper.StoreMapper;
import com.example.store.management.repository.ProductRepository;
import com.example.store.management.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Transactional
    public StoreDto saveStore(StoreDto storeDto){
        Store store=storeMapper.convert(storeDto);
        //saving store first
        storeRepository.save(store);

        //saving products
        for(Product product:store.getProduct()){
            product.setStore(store);
            productRepository.save(product);
        }
        return storeMapper.convert(store);
    }

    public List<StoreDto> getAllStores(){
        return storeRepository.findAll().stream().map(storeMapper::convert).collect(Collectors.toList());
    }

    public StoreDto deleteStore(String storeId){
        return storeRepository.findById(storeId)
                .map(store -> {
                    StoreDto storeDto=storeMapper.convert(store);
                    storeRepository.delete(store);
                    return storeDto;
                }).orElseThrow(()->new StoreNotFoundException("The store doesn't exist"));
    }

    public List<ProductDto> getProducts(String storeId){
        Store store=storeRepository.findById(storeId).get();
        List<ProductDto> productDtoList=new ArrayList<>();

        for(Product product:store.getProduct()){
            productDtoList.add(productMapper.convert(product));
        }
        return productDtoList;
    }

    public ProductDto deleteProduct(String productId,String storeId){
        Store store=storeRepository.findById(storeId).orElseThrow(()->new StoreNotFoundException("The store doesn't exist"));

        Product product=store.getProduct().stream()
                .filter(c->c.getProductId().equalsIgnoreCase(productId))
                .findFirst().orElseThrow(()->new ProductNotFoundException("The product with ID " +productId+" doesn't exist!"));

        store.getProduct().remove(product);
        storeRepository.save(store);

       return productMapper.convert(product);
    }

    public StoreDto getStoreById(String storeId){
        Store store=storeRepository.findById(storeId).orElseThrow(()->new StoreNotFoundException("The store doesn't exist"));
        return storeMapper.convert(store);
    }
}
