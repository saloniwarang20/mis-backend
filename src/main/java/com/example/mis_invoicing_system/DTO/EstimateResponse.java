package com.example.mis_invoicing_system.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EstimateResponse {

    private Long id;
    private Long zoneId;
    private String zoneName;
    private Long chainId;
    private String chainName;
    private String groupName;
    private String brandName;
    private String service;
    private Double quantity;
    private Double costPerUnit;
    private Double totalCost;
    private LocalDate deliveryDate;
    private String deliveryDetails;
    private Boolean active;

}
