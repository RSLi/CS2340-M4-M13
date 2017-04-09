package com.example.m4.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by RSLi on 2/12/17.
 */

public class User implements AccountType
{
    private String username;
    private String password;
    private HashMap profileData;

    // add permissions for users
    private List<Permission> permissions = Arrays.asList(
            Permission.ACCESS_SOURCE_REPORT,
            Permission.ACCESS_AVAILABILITY_REPORT
    );

    public User() {
        this("dummy_username", "dummy_password");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return this.permissions.contains(permission);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidPassword(String input) {
        return password.equals(input);
    }

    public HashMap getProfileData() {
        return profileData;
    }

    public void setProfileData(HashMap data) {
        this.profileData = data;
    }

    public String getType() {
        return "User";
    }

}
