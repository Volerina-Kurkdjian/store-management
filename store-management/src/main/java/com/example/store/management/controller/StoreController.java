package com.example.store.management.controller;

import com.example.store.management.dto.StoreDto;
import com.example.store.management.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.saveStore(storeDto));
    }
}
