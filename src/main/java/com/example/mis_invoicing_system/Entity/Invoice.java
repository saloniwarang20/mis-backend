package com.example.mis_invoicing_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="invoice")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="invoice_no")
    private int invoiceNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estimate_id", nullable = false)
    @JsonBackReference
    private Estimate estimate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chain_id", nullable = false)
    @JsonBackReference
    private Chain chain;

    @Column(name = "service_details", nullable = false, length = 50)
    private String serviceDetails;

    @Column(name = "qty", nullable = false)
    private Double qty;

    @Column(name = "cost_per_qty", nullable = false)
    private Double costPerQty;

    @Column(name = "amount_payable", nullable = false)
    private Double amountPayable;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "date_of_payment")
    private LocalDate dateOfPayment;

    @Column(name = "date_of_service")
    private LocalDate dateOfService;

    @Column(name = "delivery_details", length = 100)
    private String deliveryDetails;

    @Column(name = "email_id", length = 50)
    private String emailId;

    @Column(name = "is_active")
    private Boolean active = true;

}
