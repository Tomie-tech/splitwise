package com.splitwise.splitwise.dto.request;
import jakarta.validation.constraints.NotBlank;


public class RegisterRequest {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    public RegisterRequest(){}
    public RegisterRequest(String email,String password){
        this.email= email;
        this.password = password;
    }
    // getters and setters
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email){
        this.email= email;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
