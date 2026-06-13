package com.splitwise.splitwise.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // ManyToOne - many groups can be created by one user
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    // ManyToMany - many users can be in many groups
    @ManyToMany
    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;
    public Group() {}
    public Group(String name, User createdBy) {
        this.name = name;
        this.createdBy = createdBy;
        this.members = new ArrayList<>();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public User getCreatedBy() { return createdBy; }
    public List<User> getMembers() { return members; }

    public void addMember(User user) {
        members.add(user);
    }

}