//package com.scaler.demoproject.Service;
//
//import com.scaler.demoproject.Model.ListofProductDtos;
//import com.scaler.demoproject.Model.Product;
//import com.scaler.demoproject.dto.FakeStoreProductDto;
//import com.scaler.demoproject.exceptions.ProductNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("fakeStoreProductService")// a special class whose object Spring will create to be used in ProductController class
//public class FakeStoreProductService implements ProductService {
//
//    private RestTemplate restTemplate;
//
//    public FakeStoreProductService(RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
//    }
//
//
//    @Override
//    public Product getSingleProduct(Long productId) throws ProductNotFoundException{
//        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);  // getForObject gives response for an object
//
////Below code can also be used
////        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto =
////                restTemplate.getForEntity("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);    //getForEntity to get responses in the form of ResponseEntity
////
////        if(fakeStoreProductDto.getStatusCode() == HttpStatus.OK){
////            //
////        }else if(fakeStoreProductDto.getStatusCode() == HttpStatus.NOT_FOUND){
////            //handle things accordingly
//              //  throw new ProductNotFoundException("Product not found with productId" + productId);
////        }
//
//
//        //Logic for throwing exception
//        if(fakeStoreProductDto==null){
//            throw new ProductNotFoundException("Product not found with productId" + productId);
//        }
//
//
//        return fakeStoreProductDto.toProduct();// le to liye fakeStoreProductDto ke bucket me; lekin internally service me denge model bucket me na
//
//    }
//
//
//
//    @Override
//    public List<Product> getAllProducts() {
//        //For generics, or dynamic type of Lists; Lists allows you to create object of arrays or list but it does not internally define a class for it.
//        //Better use List.class
//
//        // My attempt of solution
////        ListofProductDtos  productDtos = restTemplate.getForObject("https://fakestoreapi.com/products", ListofProductDtos.class);
////        List<Product> products = new ArrayList<>();
////        for (FakeStoreProductDto p : productDtos.getLoP()){
////            products.add(p.toProduct());
////        }
////        return products;
//
//        //Sir's approach
//
////List<FakeStoreProductDto> res = restTemplate.getForObject("https://fakestoreapi.com/products", List.class);  // Compilers will anyway treat List<FakeStoreProductDto>.class as List.class
//// Important aspect here is restTemplate parses json to java object(that we specify; if any attribute does not match from object to JSON; it ignores)
//        //Passing List.class means we are not passing any attribute to check; so jackson will ignore everything
//        FakeStoreProductDto[] res = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
//
//        List<Product> products = new ArrayList<>();
//
//        for(FakeStoreProductDto fakeStoreProductDto : res){
//            products.add(fakeStoreProductDto.toProduct());
//        }
//        return products;
//    }
//
//    @Override
//    public Product createProduct(Product product) {
//
//        FakeStoreProductDto fs = new FakeStoreProductDto();
//        fs.setId(product.getId());
//        fs.setTitle(product.getTitle());
//        fs.setDescription(product.getDescription());
//        fs.setCategory(product.getCategory().getTitle());
//        fs.setPrice(product.getPrice());
//        fs.setImage(product.getImageUrl());
//
//        FakeStoreProductDto response = restTemplate.postForObject(
//                "https://fakestoreapi.com/products",
//                fs,   // data that we will be sending to fakestore
//                FakeStoreProductDto.class);  //response that we are expecting
//
//        return response.toProduct();
//
//    }
//
//    @Override
//    public Product updateProduct(Product product) {
//
//        FakeStoreProductDto fs = new FakeStoreProductDto();
//        fs.setId(product.getId());
//        fs.setTitle(product.getTitle());
//        fs.setDescription(product.getDescription());
//        fs.setCategory(product.getCategory().getTitle());
//        fs.setPrice(product.getPrice());
//        fs.setImage(product.getImageUrl());
//
//       // FakeStoreProductDto response = restTemplate.put("https://fakestoreapi.com/products/"+ fs.getId(),fs,);
//        return null;
//    }
//}
