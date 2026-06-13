package com.splitwise.splitwise.service;


import com.splitwise.splitwise.dto.request.CreateGroupRequest;
import com.splitwise.splitwise.dto.response.GroupResponse;
import com.splitwise.splitwise.dto.response.UserSummary;
import com.splitwise.splitwise.entity.Group;
import com.splitwise.splitwise.entity.User;
import com.splitwise.splitwise.repository.GroupRepository;
import com.splitwise.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public GroupResponse createGroup(CreateGroupRequest request, User currentUser) {
        Group group = new Group(request.getName(), currentUser);
        group.addMember(currentUser);

        for(Long memberId : request.getMemberIds()) {
            if(memberId.equals(currentUser.getId())) continue;
            Optional<User> member = userRepository.findById(memberId);
            if(member.isPresent()) {
                group.addMember(member.get());
            }
        }

        groupRepository.save(group);
        return toResponse(group);
    }

    public List<GroupResponse> getUserGroups(Long userId) {
        // find all groups where user is member
        // convert each to GroupResponse
        // return list
        return groupRepository.findByMembersId(userId).stream()
                .map(group -> toResponse(group))
                .toList();
    }

    private GroupResponse toResponse(Group group) {
        UserSummary createdBy = new UserSummary(
                group.getCreatedBy().getId(),
                group.getCreatedBy().getEmail()
        );
        List<UserSummary> members = group.getMembers().stream()
                .map(u -> new UserSummary(u.getId(), u.getEmail()))
                .toList();
        return new GroupResponse(group.getId(), group.getName(), createdBy, members);
    }
}