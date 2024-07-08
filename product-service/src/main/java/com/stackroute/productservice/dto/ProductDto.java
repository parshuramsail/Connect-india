package com.stackroute.productservice.dto;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    @Id
    private long id;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 250, min = 10)
    private String description;

    @NotBlank(message = "City is mandatory")
    private String city;

    private List<PlanDto> planDto;


}
