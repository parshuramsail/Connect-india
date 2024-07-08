package com.stackroute.productservice.controller;
import com.stackroute.productservice.dto.PlanDto;
import com.stackroute.productservice.dto.ProductDto;
import com.stackroute.productservice.entities.Product;
import com.stackroute.productservice.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;


    Log log = LogFactory.getLog(ProductController.class);

    @Value("${delete.message}")
    private String deletionMessage;

    /*
    * To Get All Products from database
    * */
    @GetMapping(value = "/product/fetchAllProducts")
    public List<Product> fetchAllProducts(){

        //log.info("Entered");
        return productService.fetchAllProducts();
    }

    /*
     * To SAve the Product To database
     * */
    @PostMapping(value = "/product/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){

        return new ResponseEntity<>(productService.addProduct(productDto),HttpStatus.CREATED);
    }
    /*
     * To Get Product from database
     * */
    @GetMapping(value = "/product/fetch/{id}")
    public ResponseEntity<ProductDto> fetchProductById(@RequestParam long id){

        return new ResponseEntity<>(productService.fetchProductById(id), HttpStatus.OK);
    }
    /*
     * To Update the product
     * */
    @PutMapping(value = "/product/update/{id}")
    public ResponseEntity<ProductDto> updateProductById(@RequestParam long id, @RequestBody ProductDto productDto){

        return  new ResponseEntity<>(productService.updateProductById(id, productDto), HttpStatus.OK);
    }
    /*
     * To Delete Products from database
     * */
    @DeleteMapping(value = "/product/delete/{id}")
    public ResponseEntity<String> deleteProductById(@RequestParam Long id){

        //for deleting product
        productService.deleteProductById(id);
        return  new ResponseEntity<>(deletionMessage, HttpStatus.OK);
    }
    /*
     * To Search Products from database by brand
     * */

    @GetMapping("/product/brand/{brand}")
    public ResponseEntity<ProductDto> findProductByBrand(@PathVariable String brand){
        return new ResponseEntity<>(productService.findByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("product/city/{city}")
    public ResponseEntity<List<ProductDto>> findProductByCity(@PathVariable("city") String city){
        return new ResponseEntity<>(productService.findByCity(city), HttpStatus.OK);
    }

    @GetMapping("product/selectedPlan/{brand}/{planName}/{buyerEmail}")
    public ResponseEntity<PlanDto> getByPlanNameFromProduct(@PathVariable String brand, @PathVariable String planName, @PathVariable String buyerEmail){
        return new ResponseEntity<>(productService.getPlanFromProduct(brand,planName,buyerEmail), HttpStatus.OK);
    }

    @GetMapping("product/plan/{brand}/{duration}")
    public ResponseEntity<List<PlanDto>> getByPlanDurationFromProduct(@PathVariable String brand, @PathVariable String duration){
        return  new ResponseEntity<>(productService.getPriceFromProduct(brand,duration), HttpStatus.OK);
    }
    @DeleteMapping("product/deletedPlan/{brand}/{planName}")
    public ResponseEntity<String> removePlanFromProduct(@PathVariable String brand, @PathVariable String planName){
        productService.removePlanFromProduct(brand,planName);
        return  new ResponseEntity<>(deletionMessage, HttpStatus.OK);
    }

//    @GetMapping("product/plan/addnewPlan/{brand}/{}")
//    public ResponseEntity<Product> addPlanInProduct(@PathVariable String brand){
//        return  new ResponseEntity<> (productService.addPlanInProduct(brand), HttpStatus.OK);
//    }
}
