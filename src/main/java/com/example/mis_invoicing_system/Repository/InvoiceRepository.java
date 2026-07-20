package com.example.mis_invoicing_system.Repository;

import com.example.mis_invoicing_system.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Boolean existsByInvoiceNo(Integer invoiceNo);
}
