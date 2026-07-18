package com.example.mis_invoicing_system.Controller;

import com.example.mis_invoicing_system.DTO.ChainRequest;
import com.example.mis_invoicing_system.DTO.ChainResponse;
import com.example.mis_invoicing_system.Entity.Chain;
import com.example.mis_invoicing_system.Service.ChainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chain")
public class ChainController {

    private final ChainService chainService;

    public ChainController(ChainService chainService){
        this.chainService = chainService;
    }

    //add chain
    @PostMapping
    public Chain addChain(@RequestBody ChainRequest request){
        return chainService.addChain(request);
    }

    //view chain
    @GetMapping
    public List<ChainResponse> viewAllChain(){
        return chainService.viewChains();
    }

    //update chain
    @PutMapping("/{id}")
    public Chain updateChain(@PathVariable Long id, @RequestBody ChainRequest request){
        return chainService.updateChain(id,request );
    }

    //delete chain
    @DeleteMapping("/{id}")
    public String deleteChain(@PathVariable Long id){
        return chainService.deleteChain(id);
    }

    //filter by group
    @GetMapping("/group/{groupId}")
    public List<Chain> filterByGroup(@PathVariable Long groupId){
        return chainService.getChainByGroup(groupId);
    }

    @GetMapping("/count")
    public Long totalChain(){
        return chainService.totalChains();
    }

}
