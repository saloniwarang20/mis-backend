package com.example.mis_invoicing_system.Controller;

import com.example.mis_invoicing_system.Entity.Group;
import com.example.mis_invoicing_system.Service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService){
        this.groupService = groupService;

    }

    //add group
    @PostMapping
    public Group addG(@RequestBody Group group){
        return groupService.addGroup(group);
    }

    //view all group
    @GetMapping
    public List<Group> viewAll(){
        return groupService.viewGroups();
    }

    //view active group
    @GetMapping("/active")
    public List<Group> activeGroup(){
        return groupService.activeGroup();
    }

    //view inactive group
    @GetMapping("/inactive")
    public List<Group> inactiveGroup(){
        return groupService.inactiveGroup();
    }

    //update group
    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id,@RequestBody Group group){
        return groupService.updateGroup(id,group);
    }

    //delete group
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id){
        return groupService.deleteGroup(id);
    }

    @GetMapping("/counts")
    public Map<String, Long> getCounts(){
        Map<String,Long> counts = new HashMap<>();

        counts.put("total", groupService.totalGroups());
        counts.put("active", groupService.activeGroupCounts());
        counts.put("inactive", groupService.inactiveGroupCounts());

        return counts;
    }
}
