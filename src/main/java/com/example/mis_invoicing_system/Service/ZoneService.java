package com.example.mis_invoicing_system.Service;

import com.example.mis_invoicing_system.DTO.ZoneRequest;
import com.example.mis_invoicing_system.DTO.ZoneResponse;
import com.example.mis_invoicing_system.Entity.Brand;
import com.example.mis_invoicing_system.Entity.Zone;
import com.example.mis_invoicing_system.Repository.BrandRepository;
import com.example.mis_invoicing_system.Repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final BrandRepository brandRepository;

    public Zone addZone(ZoneRequest request){
        if(zoneRepository.existsByZoneName(request.getZoneName())){
            throw new RuntimeException("Zone already exists");
        }

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(()-> new RuntimeException("Brand not found"));

        Zone zone = new Zone();

        zone.setZoneName(request.getZoneName());
        zone.setBrand(brand);
        zone.setActive(true);

        return zoneRepository.save(zone);
    }

    public Zone updateZone(Long id, ZoneRequest request){
        Zone existingZone = zoneRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Zone not found"));

        if(!existingZone.getZoneName().equals(request.getZoneName()) &&
                brandRepository.existsByBrandName(request.getZoneName())){
            throw new RuntimeException("Zone Name already exists");
        }

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(()-> new RuntimeException("Brand not found"));

        existingZone.setZoneName(request.getZoneName());
        existingZone.setBrand(brand);

        return zoneRepository.save(existingZone);
    }

    public Zone deleteZone(Long id){
        Zone existingZone = zoneRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Zone not found"));

        existingZone.setActive(false);
        return zoneRepository.save(existingZone);
    }

    public List<ZoneResponse> viewZones(){
        return zoneRepository.findAll()
                .stream()
                .map(zone -> {
                    ZoneResponse response = new ZoneResponse();

                    response.setId(zone.getId());
                    response.setZoneName(zone.getZoneName());
                    response.setActive(zone.getActive());
                    response.setBrandName(zone.getBrand().getBrandName());
                    response.setChainName(zone.getBrand().getChain().getCompanyName());
                    response.setGroupName(zone.getBrand().getChain().getGroup().getGroupName());

                    return response;
                })
                .toList();
    }

    public List<Zone> getZoneByBrand(Long brandId){
        if(!brandRepository.existsById(brandId)){
            throw new RuntimeException("Brand not found");
        }
        return zoneRepository.findByBrandId(brandId);
    }

    public Long totalZone(){
        return zoneRepository.count();
    }
}
