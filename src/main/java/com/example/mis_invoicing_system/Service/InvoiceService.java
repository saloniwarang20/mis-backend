package com.example.mis_invoicing_system.Service;

import com.example.mis_invoicing_system.DTO.InvoiceRequest;
import com.example.mis_invoicing_system.DTO.InvoiceResponse;
import com.example.mis_invoicing_system.Entity.Chain;
import com.example.mis_invoicing_system.Entity.Estimate;
import com.example.mis_invoicing_system.Entity.Invoice;
import com.example.mis_invoicing_system.Repository.EstimateRepository;
import com.example.mis_invoicing_system.Repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final EstimateRepository estimateRepository;

    // Generate Invoice
    public InvoiceResponse createInvoice(InvoiceRequest request){

        Estimate estimate = estimateRepository.findById(request.getEstimateId())
                .orElseThrow(() -> new RuntimeException("Estimate not found"));

        Invoice invoice = new Invoice();

        invoice.setInvoiceNo(generateInvoiceNumber());

        invoice.setEstimate(estimate);

        Chain chain = estimate.getZone()
                .getBrand()
                .getChain();

        invoice.setChain(chain);

        invoice.setServiceDetails(estimate.getService());
        invoice.setQty(estimate.getQuantity());
        invoice.setCostPerQty(estimate.getCostPerUnit());
        invoice.setAmountPayable(estimate.getTotalCost());
        invoice.setBalance(request.getBalance());
        invoice.setDateOfPayment(request.getDateOfPayment());
        invoice.setDateOfService(estimate.getDeliveryDate());
        invoice.setDeliveryDetails(estimate.getDeliveryDetails());
        invoice.setEmailId(request.getEmailId());
        invoice.setActive(true);

        invoiceRepository.save(invoice);

        return mapToResponse(invoice);
    }

    // View All Invoices
    public List<InvoiceResponse> getAllInvoices(){

        return invoiceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // View Single Invoice
    public InvoiceResponse getInvoice(Long id){

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return mapToResponse(invoice);
    }

    // Update Email Only
    @Transactional
    public InvoiceResponse updateInvoice(Long id, InvoiceRequest request){

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        invoice.setEmailId(request.getEmailId());

        invoiceRepository.save(invoice);

        return mapToResponse(invoice);
    }

    // Soft Delete
    public String deleteInvoice(Long id){

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        invoice.setActive(false);

        invoiceRepository.save(invoice);

        return "Invoice deleted successfully";

    }

    // Mapping
    private InvoiceResponse mapToResponse(Invoice invoice){

        InvoiceResponse response = new InvoiceResponse();

        response.setId(invoice.getId());
        response.setInvoiceNo(invoice.getInvoiceNo());

        response.setEstimateId(invoice.getEstimate().getId());

        response.setChainId(invoice.getChain().getId());

        response.setCompanyName(
                invoice.getChain().getCompanyName()
        );

        response.setGroupName(
                invoice.getChain().getGroup().getGroupName()
        );

        response.setBrandName(invoice.getEstimate().getZone().getBrand().getBrandName());

        response.setZoneName(invoice.getEstimate().getZone().getZoneName());

        response.setServiceDetails(invoice.getServiceDetails());

        response.setQty(invoice.getQty());

        response.setCostPerQty(invoice.getCostPerQty());

        response.setAmountPayable(invoice.getAmountPayable());

        response.setBalance(invoice.getBalance());

        response.setDateOfPayment(invoice.getDateOfPayment());

        response.setDateOfService(invoice.getDateOfService());

        response.setDeliveryDetails(invoice.getDeliveryDetails());

        response.setEmailId(invoice.getEmailId());

        return response;
    }

    // Generate Unique Invoice Number
    private Integer generateInvoiceNumber(){

        Random random = new Random();

        Integer invoiceNo;

        do{

            invoiceNo = 1000 + random.nextInt(9000);

        }while(invoiceRepository.existsByInvoiceNo(invoiceNo));

        return invoiceNo;
    }
}