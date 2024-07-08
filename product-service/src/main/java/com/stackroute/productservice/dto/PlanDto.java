package com.stackroute.productservice.dto;
import com.stackroute.productservice.enums.Installation;
import lombok.*;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotBlank;

//DTO class for plan
@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {

    @Id
    private long pid;

    @NotBlank(message = "Plan name is mandatory")
    private String planName;

    @NotBlank(message = "Network Speed  is mandatory")
    private String networkSpeed;

    @NotBlank(message = "Data  is mandatory")
    private String data;

    @NotBlank(message = "Price  is mandatory")
    private  double price;

    private String duration;

    private String offers;

    private Installation installation;

    private String buyerEmail;


}
