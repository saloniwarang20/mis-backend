package com.example.mis_invoicing_system.Service;

import com.example.mis_invoicing_system.DTO.BrandRequest;
import com.example.mis_invoicing_system.DTO.BrandResponse;
import com.example.mis_invoicing_system.Entity.Brand;
import com.example.mis_invoicing_system.Entity.Chain;
import com.example.mis_invoicing_system.Repository.BrandRepository;
import com.example.mis_invoicing_system.Repository.ChainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final ChainRepository chainRepository;

    public Brand addBrand(BrandRequest request){
        if(brandRepository.existsByBrandName(request.getBrandName())){
            throw new RuntimeException("Brand Name already exists");
        }

        Chain chain = chainRepository.findById(request.getChainId())
                .orElseThrow(()-> new RuntimeException("Chain does not exists"));

        Brand brand = new Brand();

        brand.setBrandName(request.getBrandName());
        brand.setActive(request.getActive());
        brand.setChain(chain);

        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, BrandRequest request){
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Brand does not exists"));

        if(!existingBrand.getBrandName().equals(request.getBrandName()) &&
        brandRepository.existsByBrandName(request.getBrandName())){
            throw new RuntimeException("Brand Name already exists");
        }

        Chain chain = chainRepository.findById(request.getChainId())
                .orElseThrow(()-> new RuntimeException("Chain not found"));

        existingBrand.setBrandName(request.getBrandName());
        existingBrand.setChain(chain);
        existingBrand.setActive(request.getActive());

        return brandRepository.save(existingBrand);
    }

    public void deleteBrand(Long id){
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Brand does not exists"));
        existingBrand.setActive(false);
        brandRepository.save(existingBrand);
    }

    public List<BrandResponse> viewBrand(){
        return brandRepository.findAll()
                .stream()
                .map(brand -> {
                    BrandResponse response = new BrandResponse();

                    response.setId(brand.getId());
                    response.setBrandName(brand.getBrandName());
                    response.setChainId(brand.getChain().getId());
                    response.setChainName(brand.getChain().getCompanyName());
                    response.setActive(true);
                    response.setGroupName(brand.getChain().getGroup().getGroupName());

                    return response;
                })
                .toList();
    }

    //filter by chain
    public List<Brand> getBrandByChain(Long chainId){
        if(!chainRepository.existsById(chainId)){
            throw new RuntimeException("Chain does not exists");
        }
        return brandRepository.findByChainId(chainId);
    }

    public Long totalBrand(){
        return brandRepository.count();
    }


}
