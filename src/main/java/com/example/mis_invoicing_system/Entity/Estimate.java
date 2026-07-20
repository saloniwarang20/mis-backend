package com.example.mis_invoicing_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="estimate")
@Data
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estimate_id")
    private Long id;

    @Column(nullable = false)
    private String service;

    private Double quantity;

    @Column(name = "cost_per_unit")
    private Double costPerUnit;

    @Column(name="total_cost")
    private Double totalCost;

    @Column(name="delivery_date")
    private LocalDate deliveryDate;

    @Column(name="delivery_details")
    private String deliveryDetails;

    @Column(name = "is_active")
    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="zone_id", nullable = false)
    @JsonBackReference
    private Zone zone;

    @OneToMany(mappedBy = "estimate")
    @JsonManagedReference
    private List<Invoice> invoiceList;


}
