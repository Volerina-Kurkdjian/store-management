package com.example.store.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {

    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String categoryId;
    private String name;
    List<ProductDto> product=new ArrayList<>();
}
