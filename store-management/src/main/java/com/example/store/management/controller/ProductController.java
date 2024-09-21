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
}
