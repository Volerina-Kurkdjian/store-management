package com.example.store.management.mapper;

import com.example.store.management.dto.CategoryDto;
import com.example.store.management.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    ModelMapper modelMapper;

    public CategoryDto convert(Category category){
        CategoryDto categoryDto=modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    public Category convert(CategoryDto categoryDto){
        Category category=modelMapper.map(categoryDto, Category.class);
        return category;
    }
}
