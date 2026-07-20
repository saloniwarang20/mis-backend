package com.example.mis_invoicing_system.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InvoiceResponse {

    private Long id;

    private Integer invoiceNo;

    private Long estimateId;

    private Long chainId;

    private String companyName;

    private String groupName;

    private String brandName;

    private String zoneName;

    private String serviceDetails;

    private Double qty;

    private Double costPerQty;

    private Double amountPayable;

    private Double balance;

    private LocalDate dateOfPayment;

    private LocalDate dateOfService;

    private String deliveryDetails;

    private String emailId;

    private Boolean isPaid;
}
