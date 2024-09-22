package com.example.store.management.dto;

import com.example.store.management.entity.Category;
import com.example.store.management.entity.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String productId;
    private String name;
    private String description;
    private Long price;

    @JsonIgnore
    private CategoryDto category;
    @JsonIgnore
    private StoreDto store;
}
