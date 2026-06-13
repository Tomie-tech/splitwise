
package com.splitwise.splitwise.dto.response;

public class UserSummary {
    private Long id;
    private String email;

    public UserSummary(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
}