package com.example.mis_invoicing_system.Repository;

import com.example.mis_invoicing_system.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByIsActiveTrue();
    List<Group> findByIsActiveFalse();

    boolean existsByGroupName(String groupName);

    long countByIsActiveTrue();
    long countByIsActiveFalse();
    long count();
}
