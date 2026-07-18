package com.example.mis_invoicing_system.DTO;

import lombok.Data;

@Data
public class ChainRequest {
    private String companyName;
    private String gstNo;
    private Long groupId;
}
