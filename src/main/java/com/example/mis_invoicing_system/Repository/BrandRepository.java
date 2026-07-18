package com.example.mis_invoicing_system.Repository;

import com.example.mis_invoicing_system.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByBrandName(String brandName);

    List<Brand> findByChainId(Long chainId);
}
