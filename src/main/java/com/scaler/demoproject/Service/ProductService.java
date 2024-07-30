package com.scaler.demoproject.Service;

import com.scaler.demoproject.Model.Product;
import com.scaler.demoproject.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//public class ProductService {
public interface ProductService { //Interfaces helps in implementing multiple versions of same thing.

    Product getSingleProduct(Long productId) throws ProductNotFoundException;
//    List<Product> getAllProducts();
//Page<Product> getAllProducts();
//Page<Product> getAllProducts(int pageSize, int pageNumber);
Page<Product> getAllProducts(int pageSize, int pageNumber,String fieldName);
    Product createProduct(Product product);
    Product updateProduct(Product product);
}



