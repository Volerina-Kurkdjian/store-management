package com.example.store.management.controller;

import com.example.store.management.dto.CategoryDto;
import com.example.store.management.dto.ProductDto;
import com.example.store.management.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(categoryDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getProducts(id));
    }

    @PutMapping("/{categoryId}/{categoryName}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId,@PathVariable String categoryName){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryId,categoryName));
    }


}
