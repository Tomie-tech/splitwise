package com.splitwise.splitwise.service;


import com.splitwise.splitwise.dto.request.CreateGroupRequest;
import com.splitwise.splitwise.dto.response.GroupResponse;

import com.splitwise.splitwise.entity.Group;
import com.splitwise.splitwise.entity.User;
import com.splitwise.splitwise.repository.GroupRepository;
import com.splitwise.splitwise.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GroupService groupService;

    @Test
    void testCreateGroup() {
        User currentUser = new User("test@gmail.com", "password");
        currentUser.setId(1L);

        CreateGroupRequest request = new CreateGroupRequest();
        request.setName("Test Group");
        request.setMemberIds(new ArrayList<>());

        Group savedGroup = new Group("Test Group", currentUser);
        savedGroup.setId(1L);
        savedGroup.addMember(currentUser); // mirror what createGroup() does
        when(groupRepository.save(any(Group.class))).thenReturn(savedGroup);

        GroupResponse response = groupService.createGroup(request, currentUser);

        assertNotNull(response);
        assertEquals("Test Group", response.getName());
        assertEquals(1L, response.getId());          // now this actually checks something
    }
    @Test
    void testGetUserGroups() {
        User currentUser = new User("test@gmail.com", "password");
        currentUser.setId(1L);

        Group group1 = new Group("Trip", currentUser);
        group1.setId(1L);
        group1.addMember(currentUser);

        Group group2 = new Group("Flatmates", currentUser);
        group2.setId(2L);
        group2.addMember(currentUser);

        when(groupRepository.findByMembersId(1L)).thenReturn(List.of(group1, group2));

        List<GroupResponse> responses = groupService.getUserGroups(1L);

        assertEquals(2, responses.size());
        assertEquals("Trip", responses.get(0).getName());
        assertEquals("Flatmates", responses.get(1).getName());
    }
}
