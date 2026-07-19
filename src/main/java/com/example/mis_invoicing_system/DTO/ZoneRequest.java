package com.example.mis_invoicing_system.DTO;

import lombok.Data;

@Data
public class ZoneRequest {

    private String zoneName;
    private Boolean active;
    private Long brandId;

}
