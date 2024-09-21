package com.example.store.management.mapper;

import com.example.store.management.dto.StoreDto;
import com.example.store.management.entity.Store;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    @Autowired
    ModelMapper modelMapper;

    public StoreDto convert(Store store){
        StoreDto storeDto=modelMapper.map(store, StoreDto.class);
        return storeDto;
    }

    public Store convert(StoreDto storeDto){
        Store store=modelMapper.map(storeDto, Store.class);
        return store;
    }

}
