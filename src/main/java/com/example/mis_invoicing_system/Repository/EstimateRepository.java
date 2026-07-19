package com.example.mis_invoicing_system.Repository;

import com.example.mis_invoicing_system.Entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    Boolean existsByService(String service);

    List<Estimate> findByZoneId(Long zoneId);
}
