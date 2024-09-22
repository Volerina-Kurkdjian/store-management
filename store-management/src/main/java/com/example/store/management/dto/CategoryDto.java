package com.example.store.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String categoryId;
    private String name;
    List<ProductDto> product=new ArrayList<>();
}
