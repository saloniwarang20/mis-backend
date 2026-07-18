package com.example.mis_invoicing_system.Controller;

import com.example.mis_invoicing_system.DTO.BrandRequest;
import com.example.mis_invoicing_system.DTO.BrandResponse;
import com.example.mis_invoicing_system.Entity.Brand;
import com.example.mis_invoicing_system.Service.BrandService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public Brand addBrand(@RequestBody BrandRequest request){
        return brandService.addBrand(request);
    }

    @Transactional
    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable Long id, @RequestBody BrandRequest request){
        return brandService.updateBrand(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id){
        brandService.deleteBrand(id);
    }

    @GetMapping
    public List<BrandResponse> viewBrand(){
        return brandService.viewBrand();
    }

    @GetMapping("/chain/{chainId}")
    public List<Brand> getBrandByChain(@PathVariable Long chainId){
        return brandService.getBrandByChain(chainId);
    }

    @GetMapping("/total")
    public Long totalBrand(){
        return brandService.totalBrand();
    }
}
