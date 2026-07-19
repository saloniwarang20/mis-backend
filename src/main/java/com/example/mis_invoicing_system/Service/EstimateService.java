package com.example.mis_invoicing_system.Service;

import com.example.mis_invoicing_system.DTO.EstimateRequest;
import com.example.mis_invoicing_system.DTO.EstimateResponse;
import com.example.mis_invoicing_system.Entity.Estimate;
import com.example.mis_invoicing_system.Entity.Zone;
import com.example.mis_invoicing_system.Repository.EstimateRepository;
import com.example.mis_invoicing_system.Repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstimateService {

    private final EstimateRepository estimateRepository;
    private final ZoneRepository zoneRepository;

    public Estimate addEstimate(EstimateRequest request){
        if(estimateRepository.existsByService(request.getService())){
            throw new RuntimeException("Service already exists");
        }

        Zone zone = zoneRepository.findById(request.getZoneId())
                .orElseThrow(()-> new RuntimeException("Zone not found"));

        Estimate estimate = new Estimate();

        estimate.setZone(zone);
        estimate.setService(request.getService());
        estimate.setQuantity(request.getQuantity());
        estimate.setCostPerUnit(request.getCostPerUnit());
        estimate.setTotalCost(request.getQuantity() * request.getCostPerUnit());
        estimate.setDeliveryDate(request.getDeliveryDate());
        estimate.setDeliveryDetails(request.getDeliveryDetails());
        estimate.setActive(true);

        return estimateRepository.save(estimate);

    }

    public Estimate updateEstimate(Long id, EstimateRequest request){
        Estimate exsitingEstimate = estimateRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("service not found"));

        if(!exsitingEstimate.getService().equals(request.getService()) && estimateRepository.existsByService(request.getService())){
            throw new RuntimeException("Service already exists");
        }

        Zone zone = zoneRepository.findById(request.getZoneId())
                .orElseThrow(()-> new RuntimeException("Zone not found"));


        exsitingEstimate.setZone(zone);
        exsitingEstimate.setService(request.getService());
        exsitingEstimate.setQuantity(request.getQuantity());
        exsitingEstimate.setCostPerUnit(request.getCostPerUnit());
        exsitingEstimate.setTotalCost(request.getQuantity() * request.getCostPerUnit());
        exsitingEstimate.setDeliveryDate(request.getDeliveryDate());
        exsitingEstimate.setDeliveryDetails(request.getDeliveryDetails());

        return estimateRepository.save(exsitingEstimate);
    }

    public Estimate deleteEstimate(Long id){
        Estimate exsitingEstimate = estimateRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("service not found"));
        exsitingEstimate.setActive(false);

        return estimateRepository.save(exsitingEstimate);
    }

    public List<EstimateResponse> viewEstimates(){
        return estimateRepository.findAll()
                .stream()
                .map((estimate)->{
                    EstimateResponse response = new EstimateResponse();

                    response.setId(estimate.getId());
                    response.setZoneId(estimate.getZone().getId());
                    response.setZoneName(estimate.getZone().getZoneName());
                    response.setChainId(estimate.getZone().getBrand().getChain().getId());
                    response.setChainName(estimate.getZone().getBrand().getChain().getCompanyName());
                    response.setGroupName(estimate.getZone().getBrand().getChain().getGroup().getGroupName());
                    response.setBrandName(estimate.getZone().getBrand().getBrandName());
                    response.setService(estimate.getService());
                    response.setQuantity(estimate.getQuantity());
                    response.setCostPerUnit(estimate.getCostPerUnit());
                    response.setTotalCost(estimate.getTotalCost());
                    response.setDeliveryDate(estimate.getDeliveryDate());
                    response.setDeliveryDetails(estimate.getDeliveryDetails());
                    response.setActive(estimate.getActive());

                    return response;
                })
                .toList();
    }

    public List<Estimate> getEstimateByZone(Long zoneId){
        if(!zoneRepository.existsById(zoneId)){
            throw new RuntimeException("Zone not found");
        }
        return estimateRepository.findByZoneId(zoneId);
    }

    public Long totalEstimate(){
        return estimateRepository.count();
    }
}
