package com.splitwise.splitwise.controller;

import com.splitwise.splitwise.dto.request.CreateGroupRequest;
import com.splitwise.splitwise.dto.request.RegisterRequest;
import com.splitwise.splitwise.dto.response.GroupResponse;
import com.splitwise.splitwise.entity.User;
import com.splitwise.splitwise.repository.UserRepository;
import com.splitwise.splitwise.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    @ResponseBody
    public GroupResponse createGroup(@Valid @RequestBody CreateGroupRequest request) {

        User currentUser = userRepository.findByEmail(getCurrentUserEmail()).orElseThrow();
        return groupService.createGroup(request, currentUser);
    }
    @GetMapping
    public List<GroupResponse> getUserGroups() {
        User currentUser = userRepository.findByEmail(getCurrentUserEmail()).orElseThrow();
        return groupService.getUserGroups(currentUser.getId());
    }

    //How to get the current logged-in user from the JWT token
    private String getCurrentUserEmail() {
        return (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
