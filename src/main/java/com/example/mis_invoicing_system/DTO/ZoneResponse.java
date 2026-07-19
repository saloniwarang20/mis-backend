package com.example.mis_invoicing_system.DTO;

import lombok.Data;

@Data
public class ZoneResponse {

    private Long id;
    private String zoneName;
    private Boolean active;
    private String brandName;
    private String chainName;
    private String groupName;
}
