package com.example.mis_invoicing_system.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EstimateRequest {

    private Long id;
    private Long zoneId;
    private String service;
    private Double quantity;
    private Double costPerUnit;
    private LocalDate deliveryDate;
    private String deliveryDetails;
}
