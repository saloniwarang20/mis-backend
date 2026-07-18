package com.example.mis_invoicing_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="brand")
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="brand_id")
    private Long id;

    @Column(name="brand_name",nullable = false)
    private String brandName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chain_id", nullable = false)
    @JsonBackReference
    private Chain chain;

    @Column(name="isActive")
    private Boolean active = true;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
