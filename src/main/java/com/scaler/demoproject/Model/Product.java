package com.scaler.demoproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel{
    //private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    @ManyToOne//to avoid the checks we did in createProduct of selfProductService, use @ManyToOne(cascade = {CascadeType.Persist})
    private Category category ;

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                '}';
    }
}
