package com.example.mis_invoicing_system.Service;

import com.example.mis_invoicing_system.DTO.ChainRequest;
import com.example.mis_invoicing_system.DTO.ChainResponse;
import com.example.mis_invoicing_system.Entity.Chain;
import com.example.mis_invoicing_system.Entity.Group;
import com.example.mis_invoicing_system.Repository.ChainRepository;
import com.example.mis_invoicing_system.Repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChainService {

    private final ChainRepository chainRepository;
    private final GroupRepository groupRepository;

    //add a chain
    public Chain addChain(ChainRequest request){
        if(chainRepository.existsByCompanyName(request.getCompanyName())){
            throw new RuntimeException("Chain name already exists");
        }

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(()-> new RuntimeException("Group not found"));

        Chain chain = new Chain();

        chain.setCompanyName(request.getCompanyName());
        chain.setGstNo(request.getGstNo());
        chain.setGroup(group);
        chain.setIsActive(true);
        return chainRepository.save(chain);
    }

    //view all chains
    public List<ChainResponse> viewChains(){
        return chainRepository.findAll().stream().map(chain -> {
            ChainResponse response = new ChainResponse();

            response.setId(chain.getId());
            response.setCompanyName(chain.getCompanyName());
            response.setGstNo(chain.getGstNo());
            response.setIsActive(chain.getIsActive());

            response.setGroupId(chain.getGroup().getId());
            response.setGroupName(chain.getGroup().getGroupName());

            return response;
        }).toList();
    }

    //update chain
    @Transactional
    public Chain updateChain(long id,ChainRequest request){
        Chain existingChain = chainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chain not found"));


        if(!existingChain.getCompanyName().equals(request.getCompanyName())
            && chainRepository.existsByCompanyName(request.getCompanyName())){
            throw new RuntimeException("Company name already exists");
        }

        Group group = groupRepository.findById(request.getGroupId())
                        .orElseThrow(()->new RuntimeException("Group id not found"));

        existingChain.setGroup(group);
        existingChain.setCompanyName(request.getCompanyName());
        existingChain.setGstNo(request.getGstNo());

        return chainRepository.save(existingChain);
    }

    //delete chain
    public String deleteChain(Long id){
        Chain existingChain = chainRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Chain not found"));
        existingChain.setIsActive(false);
        chainRepository.save(existingChain);
        return "Chain deleted successfully";
    }

    //filter by group
    public List<Chain> getChainByGroup(Long groupId){
        if(!groupRepository.existsById(groupId)){
            throw new RuntimeException("Group not found");
        }

        return chainRepository.findByGroupId(groupId);
    }

    public long totalChains(){
        return chainRepository.count();
    }
}
