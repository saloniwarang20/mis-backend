package com.example.mis_invoicing_system.Controller;

import com.example.mis_invoicing_system.DTO.InvoiceRequest;
import com.example.mis_invoicing_system.DTO.InvoiceResponse;
import com.example.mis_invoicing_system.Service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvoiceController {

    private final InvoiceService invoiceService;

    // Create Invoice
    @PostMapping
    public InvoiceResponse createInvoice(@RequestBody InvoiceRequest request) {
        return invoiceService.createInvoice(request);
    }

    // View All Invoices
    @GetMapping
    public List<InvoiceResponse> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    // Update Invoice
    @PutMapping("/{id}")
    public InvoiceResponse updateInvoice(
            @PathVariable Long id,
            @RequestBody InvoiceRequest request) {

        return invoiceService.updateInvoice(id, request);
    }

    // Soft Delete Invoice
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return "Invoice deleted successfully.";
    }
}