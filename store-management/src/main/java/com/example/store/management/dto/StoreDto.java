package com.example.store.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String storeId;
    private String name;
    private String contactInfo;
    List<ProductDto> product=new ArrayList<>();
}
