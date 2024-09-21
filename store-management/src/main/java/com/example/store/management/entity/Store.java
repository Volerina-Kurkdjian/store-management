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
@Builder
public class Store {

    @Id
    @Column(name="store_id")
    @GeneratedValue(strategy= GenerationType.UUID)
    private String storeId;
    private String name;
    private String contactInfo;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Product> product=new ArrayList<>();

    public void addProduct(Product productToAdd){
        product.add(productToAdd);
        productToAdd.setStore(this);
    }

    public void removeProduct(Product productToRemove){
        product.remove(productToRemove);
        productToRemove.setStore(null);
    }
}
