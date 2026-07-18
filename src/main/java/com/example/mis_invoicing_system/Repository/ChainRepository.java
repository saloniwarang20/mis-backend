package com.example.mis_invoicing_system.Repository;

import com.example.mis_invoicing_system.Entity.Chain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChainRepository extends JpaRepository<Chain, Long> {

    boolean existsByCompanyName(String chainName);

    List<Chain> findByGroupId(Long groupId);
}
