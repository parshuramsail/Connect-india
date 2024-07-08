package com.stackroute.dto;
import lombok.*;
import java.util.List;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private long id;

    private String brand;

    private String description;

    private List<PlanDto> planDto;


}
