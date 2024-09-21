package com.example.store.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryId;

    @NonNull
    @Column
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    List<Product> product=new ArrayList<>();

    public void addProduct(Product productToAdd){
        product.add(productToAdd);
        productToAdd.setCategory(this);
    }

    public void removeProduct(Product productToRemove){
        product.remove(productToRemove);
        productToRemove.setCategory(null);
    }

}
