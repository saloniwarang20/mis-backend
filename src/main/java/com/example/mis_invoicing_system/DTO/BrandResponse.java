package com.example.mis_invoicing_system.DTO;

import lombok.Data;

@Data
public class BrandResponse {

    private Long id;
    private String brandName;
    private Boolean active;
    private Long chainId;
    private String chainName;
    private String groupName;
}
