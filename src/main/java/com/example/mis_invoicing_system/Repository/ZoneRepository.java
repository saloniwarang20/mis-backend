package com.example.mis_invoicing_system.Repository;

import com.example.mis_invoicing_system.Entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Boolean existsByZoneName(String zoneName);

    List<Zone> findByBrandId(Long brandId);
}
