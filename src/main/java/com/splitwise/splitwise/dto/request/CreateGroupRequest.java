package com.splitwise.splitwise.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class CreateGroupRequest {

    @NotBlank(message = "Group name is required")
    private String name;

    private List<Long> memberIds;

    public CreateGroupRequest() {}

    public String getName() { return name; }
    public List<Long> getMemberIds() { return memberIds; }
    public void setName(String name) { this.name = name; }
    public void setMemberIds(List<Long> memberIds) { this.memberIds = memberIds; }
}