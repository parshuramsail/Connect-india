package com.stackroute.productservice.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

//Modal class for Product
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @Transient
    public static final String SEQUENCE_NAME = "product_sequence";

    @Id
    private long id;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 250, min = 10)
    private String description;

    @NotBlank(message = "City is mandatory")
    private String city;

    private List<Plan> plan;

}
