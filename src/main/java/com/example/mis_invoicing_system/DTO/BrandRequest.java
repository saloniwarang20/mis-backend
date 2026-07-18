package com.example.mis_invoicing_system.DTO;

import lombok.Data;

@Data
public class BrandRequest {

    private String brandName;
    private Boolean active;
    private Long chainId;
}
