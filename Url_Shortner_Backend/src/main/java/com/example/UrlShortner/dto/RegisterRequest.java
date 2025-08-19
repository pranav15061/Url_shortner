package com.example.UrlShortner.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Data
@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String email;
    private Set<String> role;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
