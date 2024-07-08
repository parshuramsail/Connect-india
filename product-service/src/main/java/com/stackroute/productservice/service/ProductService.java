package com.stackroute.productservice.service;
import com.stackroute.productservice.dto.PlanDto;
import com.stackroute.productservice.dto.ProductDto;
import com.stackroute.productservice.entities.Product;
import java.util.List;


public interface ProductService {

    List<Product> fetchAllProducts();

    ProductDto addProduct(ProductDto productDto);

    ProductDto fetchProductById(long id);

    ProductDto updateProductById(long id, ProductDto productDto);

    void deleteProductById(long id);

    ProductDto findByBrand(String brand);

    List<ProductDto> findByCity(String city);

    PlanDto getPlanFromProduct(String brand, String planName, String buyerEmail);


    List<PlanDto> getPriceFromProduct(String brand, String duration);

    Product removePlanFromProduct(String brand, String planName);

    //Product addPlanInProduct(String brand);
}
