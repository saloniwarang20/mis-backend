package com.example.mis_invoicing_system.Controller;

import com.example.mis_invoicing_system.DTO.ZoneRequest;
import com.example.mis_invoicing_system.DTO.ZoneResponse;
import com.example.mis_invoicing_system.Entity.Zone;
import com.example.mis_invoicing_system.Service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/zone")
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    public Zone addZone(@RequestBody ZoneRequest request){
        return zoneService.addZone(request);
    }

    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable Long id, @RequestBody ZoneRequest request){
        return zoneService.updateZone(id,request);
    }

    @DeleteMapping("/{id}")
    public Zone deleteZone(@PathVariable Long id){
        return zoneService.deleteZone(id);
    }

    @GetMapping
    public List<ZoneResponse> viewZone(){
        return zoneService.viewZones();
    }

    @GetMapping("/brand/{brandId}")
    public List<Zone> getZoneByBrand(@PathVariable Long brandId){
        return zoneService.getZoneByBrand(brandId);
    }

    @GetMapping("/total")
    public Long totalZone(){
        return zoneService.totalZone();
    }
}
