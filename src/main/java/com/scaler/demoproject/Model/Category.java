package com.scaler.demoproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseModel {
    //making members as private will ensure in no way we can access this field in different class files
    //How other classes or files will access these is through getters and setters
    //see benefits of it
    //we can create getters and setter for all members (right click-->generate getters and setter)
    //Another way, using annotation @Getter and @Setter {Lombok dependency} and Spring will make sure we can getter and setter methods accordingly
    //Similarly for Constructors, @AllArgsConsturctor and @NoArgsConstructor

    //private Long id;
    private String title;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;


}
