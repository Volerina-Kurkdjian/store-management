package com.example.store.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class StoreDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String storeId;
    private String name;
    private String contactInfo;
    List<ProductDto> product=new ArrayList<>();
}
