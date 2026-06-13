package com.splitwise.splitwise.dto.response;
import java.util.*;

public class GroupResponse {
    private Long id;
    private String name;
    private UserSummary createdBy;
    private List<UserSummary> members;

    public GroupResponse(long id, String name, UserSummary createdBy, List<UserSummary> members){
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.members = members;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public UserSummary getCreatedBy(){return createdBy; }
    public List<UserSummary> getMembers(){return members;}
}
