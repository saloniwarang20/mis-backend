package com.example.mis_invoicing_system.Service;

import com.example.mis_invoicing_system.Entity.Group;
import com.example.mis_invoicing_system.Repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    //add group
    public Group addGroup(Group group){

        if(groupRepository.existsByGroupName(group.getGroupName())){
            throw new RuntimeException("Group name already exists");
        }

        group.setIsActive(true);
        return groupRepository.save(group);
    }

    //view all group
    public List<Group> viewGroups(){
        return groupRepository.findAll();
    }

    //view active groups
    public List<Group> activeGroup(){
        return groupRepository.findByIsActiveTrue();
    }

    //view inactive groups
    public List<Group> inactiveGroup(){
        return groupRepository.findByIsActiveFalse();
    }

    //update group
    @Transactional
    public Group updateGroup(Long id, Group groupDetails){
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id "+id));

        if(!existingGroup.getGroupName().equals(groupDetails.getGroupName())
                && groupRepository.existsByGroupName(groupDetails.getGroupName())){
            throw new RuntimeException("Group name already exists");
        }

        existingGroup.setGroupName(groupDetails.getGroupName());
        existingGroup.setIsActive(groupDetails.getIsActive());

        return groupRepository.save(existingGroup);
    }

    //soft-delete group
    public String deleteGroup(Long id){
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(("Group not found with id "+id)));

        existingGroup.setIsActive(false);
        groupRepository.save(existingGroup);
        return "Group de-activated successfully";
    }

    public long totalGroups(){
        return groupRepository.count();
    }
    public long activeGroupCounts(){
        return groupRepository.countByIsActiveTrue();
    }
    public long inactiveGroupCounts(){
        return groupRepository.countByIsActiveFalse();
    }


}
