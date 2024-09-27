package com.example.store.management.entity;


import com.example.store.management.configuration.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    //this is a regex we need to implement the annotation
   // @Email(message="The email must be a valid email")
    @Pattern(regexp = Constants.EMAIL_REGULAR_EXPRESSION,message = "Email must be valid")
    private String email;

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
