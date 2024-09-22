package com.example.store.management.controller;

import com.example.store.management.dto.ProductDto;
import com.example.store.management.dto.StoreDto;
import com.example.store.management.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.saveStore(storeDto));
    }

    @GetMapping("/getStores")
    public ResponseEntity<List<StoreDto>> getAllStores(){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getAllStores());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StoreDto> deleteStore(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.deleteStore(id));
    }

    @GetMapping("/getProducts/{idStore}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable(name="idStore") String id){
        return ResponseEntity.ok().body(storeService.getProducts(id));
    }

    @DeleteMapping("/deleteProduct/{productId}/{idOfStore}")
    public ResponseEntity<ProductDto> deleteProductFromStore(@PathVariable String productId,@PathVariable(name="idOfStore") String id){
        return ResponseEntity.ok().body(storeService.deleteProduct(productId,id));
    }

    @GetMapping("/getStore/{storeId}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable(name="storeId") String id){
        return ResponseEntity.ok(storeService.getStoreById(id));
    }
}
