package com.example.store.management.controller;

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
        return ResponseEntity.status(HttpStatus.OK).body(storeService.saveStore(storeDto));
    }

    @GetMapping("/getStores")
    public ResponseEntity<List<StoreDto>> getAllStores(){
        return ResponseEntity.ok().body(storeService.getAllStores());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StoreDto> deleteStore(@PathVariable String id){
        return ResponseEntity.ok().body(storeService.deleteStore(id));
    }
}
