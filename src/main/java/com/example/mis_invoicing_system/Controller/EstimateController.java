package com.example.mis_invoicing_system.Controller;

import com.example.mis_invoicing_system.DTO.EstimateRequest;
import com.example.mis_invoicing_system.DTO.EstimateResponse;
import com.example.mis_invoicing_system.DTO.ZoneRequest;
import com.example.mis_invoicing_system.Entity.Estimate;
import com.example.mis_invoicing_system.Service.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/estimate")
public class EstimateController {

    private final EstimateService estimateService;

    @PostMapping
    public Estimate addEstimate(@RequestBody EstimateRequest request){
        return estimateService.addEstimate(request);
    }

    @PutMapping("/{id}")
    public Estimate updateEstimate(@PathVariable Long id, @RequestBody EstimateRequest request){
        return estimateService.updateEstimate(id, request);
    }

    @DeleteMapping("/{id}")
    public Estimate deleteEstimate(@PathVariable Long id){
        return estimateService.deleteEstimate(id);
    }

    @GetMapping
    public List<EstimateResponse> viewEstimate(){
        return estimateService.viewEstimates();
    }

    @GetMapping("/zone/{zoneId}")
    public List<Estimate> getEstimateByZone(@PathVariable Long zoneId){
        return estimateService.getEstimateByZone(zoneId);
    }

    @GetMapping("/total")
    public Long totalEstimate(){
        return estimateService.totalEstimate();
    }
}
