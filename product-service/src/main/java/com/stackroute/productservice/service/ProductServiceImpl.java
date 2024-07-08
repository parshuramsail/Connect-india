package com.stackroute.productservice.service;

import com.stackroute.productservice.config.Producer;
import com.stackroute.productservice.dto.PlanDto;
import com.stackroute.productservice.dto.ProductDto;
import com.stackroute.productservice.entities.Plan;
import com.stackroute.productservice.entities.Product;
import com.stackroute.productservice.exception.IdNotFoundException;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.repository.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    Log log = LogFactory.getLog(ProductServiceImpl.class);
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Autowired
    Producer producer;

    @Value("${id.not.found.exception.message}")
    private String idNotFoundMessage;
    @Value("${product.not.found.exception.message}")
    private String productNotFoundMessage;

    /*
     * @Description :  this method is used to fetch All Product Details
     * @Param:
     * @returns: It Returns All the Product details which is present in the db
     * @throws:
     * @Created By: shuvadip Moulick
     * @Created date: 27th November 2022
     *
     * */
    @Override
    public List<Product> fetchAllProducts() {

        return  productRepository.findAll();
    }
    /*
     * @Description :  this method is used to Register the Product
     * @Param: It takes product details as a signature
     * @returns: It Returns Successfull message with the product details
     * @throws:
     * @Created By: shuvadip Moulick
     * @Created date: 27th November 2022
     *
     * */
    @Override
    public ProductDto addProduct(ProductDto productDto) {

        //if(productRepository.findByBrand(productDto.getBrand()) != null ) throw new RuntimeException("Brand already exists");

        Product product = new Product();
        product = convertToEntity(productDto,product);

        product.setId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));

        List<Plan> plans = product.getPlan();
        for (Plan plan:plans){
            plan.setPid(sequenceGeneratorService.generateSequence(Plan.SEQUENCE_NAME));
        }
        productRepository.save( product);
        log.info("Product successfully created and stored in database");

        ProductDto productDto1 = convertToDto(product);

        producer.sendMessageToRabbitMq(productDto1);
        return productDto1;
    }

    /*
     * @Description :  this method is used to fetch existing product details by product id
     * @Param: It takes product id as a parameter
     * @returns: It Returns Product Details According to product id
     * @throws: It throws IdNotFoundException
     * @Created By: shuvadip Moulick
     * @Created date: 22nd November 2022
     *
     * */
    @Override
    public ProductDto fetchProductById(long id) {
        log.info("Getting Product details by Product Id");
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        {
                            log.warn("Id is not found in DB");
                            return new IdNotFoundException(idNotFoundMessage + id);
                        }
                );
        log.info("Got product details Successfully");

       // ProductDto productDto = convertToDto(product);

       // producer.sendMessageToRabbitMq(productDto);

        return convertToDto(product);
    }

    /*
     * @Description :  this method is used to update existing product details
     * @Param: It takes product id as a parameter and the modified data as a parameter
     * @returns: It Returns modified product details
     * @throws: It throws IdNotFoundException
     * @Created By: shuvadip Moulick
     * @Created date: 23rd November 2022
     *
     * */
    @Override
    public ProductDto updateProductById(long id, ProductDto productDto) {
        log.info("Getting Product details by Product Id");
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        {
                            log.warn("Id is not found in DB");
                            return new IdNotFoundException(idNotFoundMessage + id);
                        }
                );

        log.info("Save Update Product Details in database");
        return convertToDto(productRepository.save( convertToEntity(productDto, product)));
    }

    /*
     * @Description :  this method is used to delete existing product details
     * @Param: It takes product id as a parameter
     * @returns: It Returns delete message and deleted the Product Details According to product id
     * @throws: It throws IdNotFoundException
     * @Created By: shuvadip Moulick
     * @Created date: 23rd November 2022
     *
     * */
    @Override
    public void deleteProductById(long id) {

        log.info("Getting product details by product id");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(idNotFoundMessage + id));

        log.info("Delete Product Details successfully");
        productRepository.delete(product);
    }

    /*
    * @Description :  this method is used to get product by brand
    * */
    @Override
    public ProductDto findByBrand(String brand) {
        Product products = productRepository.findByBrand(brand);
        log.info("Finding by brand");
        log.info(products.toString());

        if (products == null){
            log.error("Exception shown in method findBybrand");
            throw new ProductNotFoundException(productNotFoundMessage + brand);
        }

        return convertToDto(products);
    }
    /*
     * @Description :  this method is used to get product by city
     * */
    @Override
    public List<ProductDto> findByCity(String city) {
        List<Product> products = productRepository.findByCity(city);
        log.info("Finding by City");
        log.info(products.toString());

        if (products.isEmpty()){
            log.error("Exception shown in method findByCity");
            throw  new ProductNotFoundException(productNotFoundMessage + city);
        }
        List<ProductDto> dtos = new ArrayList<>();
        for (Product product: products){
            dtos.add(convertToDto(product));
        }
        return dtos;
    }

    /*
     * @Description :  this method is used to get plan details by Product name along with plan name
     * */

    @Override
    public PlanDto getPlanFromProduct(String brand, String planName, String buyerEmail)throws ProductNotFoundException {
      Product product = (Product) productRepository.findByBrand(brand);

      if (product != null){
       List<Plan> plans =   product.getPlan();
        PlanDto planDto = new PlanDto();
       for (Plan plan:plans){

           if (plan.getPlanName().equals(planName)){
               planDto.setPid(plan.getPid());
               planDto.setPlanName(plan.getPlanName());
               planDto.setNetworkSpeed(plan.getNetworkSpeed());
               planDto.setData(plan.getData());
               planDto.setPrice(plan.getPrice());
               planDto.setDuration(plan.getDuration());
               planDto.setOffers(plan.getOffers());
               planDto.setInstallation(plan.getInstallation());
               planDto.setBuyerEmail(buyerEmail);

               producer.sendMessageToRabbitMq(planDto);
               return planDto;
           }
       }
      }
        return null;
    }

    @Override
    public List<PlanDto> getPriceFromProduct(String brand, String duration) {
       Product product = productRepository.findByBrand(brand);

        List<PlanDto> planDtoList = new ArrayList<>();
       if (product != null){
           List<Plan> plans = product.getPlan();

           for (Plan plan:plans){
               if (plan.getDuration().equals(duration)){

                   PlanDto planDto = new PlanDto();

                   planDto.setPid(plan.getPid());
                   planDto.setPlanName(plan.getPlanName());
                   planDto.setNetworkSpeed(plan.getNetworkSpeed());
                   planDto.setData(plan.getData());
                   planDto.setPrice(plan.getPrice());
                   planDto.setDuration(plan.getDuration());
                   planDto.setOffers(plan.getOffers());
                   planDto.setInstallation(plan.getInstallation());

                   planDtoList.add(planDto);

               }
           }
       }

        return planDtoList;
    }

    @Override
    public Product removePlanFromProduct(String brand, String planName) {

     Product product =   productRepository.findByBrand(brand);

        if (product == null){
            log.error("Exception shown in method findBybrand");
            throw new ProductNotFoundException(productNotFoundMessage + brand);
        } else if (product != null) {
         List<Plan> planList = product.getPlan();

         for (int i =0; i< planList.size(); i++){
             if (planList.get(i).getPlanName().equals(planName)){
                 planList.remove(i);
                 break;
             }
         }
         product.setPlan(planList);
         return productRepository.save(product);
     }
        return null;
    }

//    @Override
//    public Product addPlanInProduct(String brand, List<Plan> plan) {
//
//      Product product =  productRepository.findByBrand(brand);
//        if (product == null){
//            log.error("Exception shown in method findBybrand");
//            throw new ProductNotFoundException(productNotFoundMessage + brand);
//        } else if (product != null) {
//            Product product1 = new Product();
//            List<Plan> plans = product.getPlan();
//            for (Plan plan1:plans){
//                plan1.setPid(sequenceGeneratorService.generateSequence(Plan.SEQUENCE_NAME));
//            }
//            productRepository.save( product1);
//
//            return product1;
//
//        }
//
//        return null;
//    }
    /*
     * @Description :  this method is used to get plan details by Product name along with plan network speed
     * */
//

    /*
     * It Converts User Entity to User DTO
     */
    private ProductDto convertToDto(Product product){
        List<PlanDto> planDtos =new ArrayList<>();
        List<Plan> plans = product.getPlan();

        for (Plan plan: plans ){

            PlanDto planDto = new PlanDto();

            planDto.setPid(plan.getPid());
            planDto.setPlanName(plan.getPlanName());
            planDto.setNetworkSpeed(plan.getNetworkSpeed());
            planDto.setData(plan.getData());
            planDto.setPrice(plan.getPrice());
            planDto.setDuration(plan.getDuration());
            planDto.setOffers(plan.getOffers());
            planDto.setInstallation(plan.getInstallation());

            planDtos.add(planDto);
        }

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setBrand(product.getBrand());
        productDto.setDescription(product.getDescription());
        productDto.setCity(product.getCity());
        productDto.setPlanDto(planDtos);

        return productDto;

    }
    /*
     * It Converts User DTO to User Entity
     */
    private  Product convertToEntity(ProductDto productDto, Product product){
        List<Plan> plans = new ArrayList<>();
        List<PlanDto> planDtos = productDto.getPlanDto();
        for (PlanDto dto: planDtos){

            Plan plan = new Plan();


            plan.setPlanName(dto.getPlanName());
            plan.setNetworkSpeed(dto.getNetworkSpeed());
            plan.setData(dto.getData());
            plan.setPrice(dto.getPrice());
            plan.setDuration(dto.getDuration());
            plan.setOffers(dto.getOffers());
            plan.setInstallation(dto.getInstallation());

            plans.add(plan);
        }

        product.setBrand(productDto.getBrand());
        product.setDescription(productDto.getDescription());
        product.setCity(productDto.getCity());
        product.setPlan(plans);

        return product;
    }




}



