package com.scaler.demoproject.dto;

import com.scaler.demoproject.Model.Category;
import com.scaler.demoproject.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct(){
        Product p = new Product();
        p.setId(id);
        p.setTitle(title);
        p.setPrice(price);
        p.setDescription(description);

        Category cat = new Category();
        cat.setTitle(category);
        p.setCategory(cat);

        p.setImageUrl(image);
        return p;
    }
}
