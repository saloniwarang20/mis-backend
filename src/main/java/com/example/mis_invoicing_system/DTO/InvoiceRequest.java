package com.example.mis_invoicing_system.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InvoiceRequest {

    private Long estimateId;

    private Long chainId;

    private String serviceDetails;

    private Double qty;

    private Double costPerQty;

    private Double amountPayable;

    private Double balance;

    private LocalDate dateOfPayment;

    private LocalDate dateOfService;

    private String deliveryDetails;

    private String emailId;
}
