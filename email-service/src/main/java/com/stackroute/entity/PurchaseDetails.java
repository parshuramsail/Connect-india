package com.stackroute.entity;

import com.stackroute.dto.PlanDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetails {
    private String product;
    private  String details;

    private List<PlanDto> plans;
    private String price;
}
