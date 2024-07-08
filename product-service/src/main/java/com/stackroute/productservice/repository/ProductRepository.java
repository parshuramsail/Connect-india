package com.stackroute.productservice.repository;
import com.stackroute.productservice.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface ProductRepository extends MongoRepository<Product, Long>{
 Product findByBrand(String brand);

 List<Product> findByCity(String city);

}
