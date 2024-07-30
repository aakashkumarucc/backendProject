package com.scaler.demoproject;

import com.scaler.demoproject.Model.Category;
import com.scaler.demoproject.Model.Product;
import com.scaler.demoproject.repositories.CategoryRepository;
import com.scaler.demoproject.repositories.ProductRepository;
import com.scaler.demoproject.repositories.projections.ProductProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoProjectApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void TestQueries(){
        //List<Product> products = productRepository.findAll();

//        List<Product> products = productRepository.getProductsByCategoryId(2L);
//        System.out.println(products.get(0));

//        List<Product> products = productRepository.findByCategoryIdWithNativeQuery(2L);
//       System.out.println(products.get(0));

//        List<ProductProjection> projections = productRepository.getProductProjectionsByCategoryId(2L);
//        System.out.println(projections.get(0).getId());


    }
    @Test
    void fetchCategoryLazy(){

        //Lazy first call
        Category category = categoryRepository.findById(2L).get();
        System.out.println(category.getId());
        System.out.println("We are done here");

//    Lazy 2nd call
//        List<Product> currentProducts = category.getProducts();
//        System.out.println(currentProducts.size());
        //above both lines were need for the 2nd  call to happen
        // It is going to execute a new query to fetch list of products

        System.out.println("Products fetched");
    }


}
