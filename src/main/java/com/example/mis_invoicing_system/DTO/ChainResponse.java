package com.example.mis_invoicing_system.DTO;

import lombok.Data;

@Data
public class ChainResponse {
    private Long id;
    private String companyName;
    private String gstNo;
    private Boolean isActive;

    private Long groupId;
    private String groupName;
}
