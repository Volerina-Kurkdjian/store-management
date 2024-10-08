package com.example.store.management.controller;

import com.example.store.management.dto.ProductDto;
import com.example.store.management.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProduct(productDto));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String productId){
        return ResponseEntity.ok().body(productService.deleteProduct(productId));
    }

    @PutMapping("/register/{productId}/{categoryName}")
    public ResponseEntity<ProductDto> addProductToCategory(@PathVariable String productId, @PathVariable String categoryName){
        return ResponseEntity.ok().body(productService.registerProduct(productId,categoryName));
    }

    @PutMapping("/addProductToStore/{productId}/{storeId}")
    public ResponseEntity<ProductDto> registerProductToStore(@PathVariable String productId, @PathVariable String storeId){
        return ResponseEntity.ok().body(productService.registerProductToStore(productId,storeId));
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId,@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId,productDto));
    }

}
