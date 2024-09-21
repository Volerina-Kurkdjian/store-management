package com.example.store.management.service;


import com.example.store.management.dto.StoreDto;
import com.example.store.management.entity.Product;
import com.example.store.management.entity.Store;
import com.example.store.management.mapper.ProductMapper;
import com.example.store.management.mapper.StoreMapper;
import com.example.store.management.repository.ProductRepository;
import com.example.store.management.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
