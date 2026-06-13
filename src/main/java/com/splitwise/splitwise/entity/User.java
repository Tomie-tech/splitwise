package com.splitwise.splitwise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(){}
    public User(String email,String password){
        this.email = email;
        this.password = password;
    }
    public Long getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password= password;
    }

}
