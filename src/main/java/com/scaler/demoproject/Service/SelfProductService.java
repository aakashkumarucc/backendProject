package com.scaler.demoproject.Service;

import com.scaler.demoproject.Model.Category;
import com.scaler.demoproject.Model.Product;
import com.scaler.demoproject.exceptions.ProductNotFoundException;
import com.scaler.demoproject.repositories.CategoryRepository;
import com.scaler.demoproject.repositories.ProductRepository;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(productId);
        if(p.isPresent()){
            return p.get();
        }else{
            throw new ProductNotFoundException("Product not found");
        }

    }
//    @Override
//    public List<Product> getAllProducts() {
//        List<Product> allProducts = productRepository.findAll();
//        return allProducts;
//        //return List.of();
//    }

//    @Override
//    public Page<Product> getAllProducts() {
//        Page<Product> allProducts = productRepository.findAll(PageRequest.of(2,1));
//        return allProducts;
//        //return List.of();
//    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNo, String fieldName) {
        Page<Product> allProducts = productRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by(fieldName).ascending()));
        return allProducts;
        //return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        //For creating a product; I will have to first check that category exists or not; if not, create it first.
        Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
        if(cat == null){
            //no category with our title in DB
            Category newCat = new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow = categoryRepository.save(newCat);
            product.setCategory(newRow);
        }else{
            product.setCategory(cat);
        }
        Product savedProduct = productRepository.save(product);
        return savedProduct;

        // Instead doing all this checks manually,
        //use @ManyToOne(cascade = {CascadeType.Persist}) in Product model
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
