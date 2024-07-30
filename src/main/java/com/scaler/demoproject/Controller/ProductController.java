package com.scaler.demoproject.Controller;

//Please note Actutator also needs a dependency to be enabled
import com.scaler.demoproject.Model.Product;
import com.scaler.demoproject.Service.ProductService;
import com.scaler.demoproject.dto.ErrorDto;
import com.scaler.demoproject.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//JVM scans through all the files and with this annotation it figures, this is where a bunch of apis are there
public class ProductController {

    //    //POST /product
//    Request Body
//    {
//        "id": 20,
//            "title": "DANVOUY Womens T Shirt Casual Cotton Short",
//            "price": 12.99,
//            "description": "95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.",
//            "category": "women's clothing",
//            "image": "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg",
//            "rating": {
//        "rate": 3.6,
//                "count": 145
//    }
//    }

    private ProductService productService;//we haven't  create the object using new keyword; we need Spring to do this;  first create an object of this

    public ProductController(@Qualifier("selfProductService") ProductService productService) {

        this.productService = productService;
    }


    //@RequestMapping(value = "/products", produces = "application/json",method = RequestMethod.POST)
//    @PostMapping("/products")//Whenever someone hits /products with POST request , please execute below method
//    public ResponseEntity<Product> createProduct(@RequestBody Product product){
//        Product postRequestResponse = productService.createProduct(product);
//        return new ResponseEntity<>(postRequestResponse,HttpStatus.CREATED);
//    }
    @PostMapping("/products")//Whenever someone hits /products with POST request , please execute below method
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product postRequestResponse = productService.createProduct(product);
        return new ResponseEntity<>(postRequestResponse,HttpStatus.CREATED);
    }

    @GetMapping(    "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ProductNotFoundException {   /// Sir used @PathVariable("id") Long productId
        Product currentProduct = productService.getSingleProduct(id);
        ResponseEntity<Product> res = new ResponseEntity<>(
                currentProduct,
                HttpStatus.OK    ///just for illustration
        );
        return res;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/")
    public Page<Product> getAllProduct(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber,@RequestParam("sortBy") String  fieldName){ // sortOrder can also be passed

        return productService.getAllProducts(pageSize,pageNumber,fieldName);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        product.setId(id);
        return productService.updateProduct(product);
    }
}
