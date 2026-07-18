package com.example.mis_invoicing_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chain")
@Data
public class Chain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chain_id")
    private Long id;

    @Column(name="company_name",nullable = false)
    private String companyName;

    @Column(name="gst_no",unique = true, nullable = false, length = 15)
    private String gstNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id", nullable = false)
    @JsonBackReference
    private Group group;

    @Column(name="is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "chain")
    @JsonManagedReference
    private List<Brand> brand;
}
