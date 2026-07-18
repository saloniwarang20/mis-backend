package com.example.mis_invoicing_system.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="`groups`")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="group_id", unique = true)
    private Long id;

    @Column(name="group_name", unique = true, nullable = false)
    private String groupName;

    @Column(name="is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private List<Chain> chains;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
