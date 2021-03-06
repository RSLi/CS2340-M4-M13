package com.example.m4.models;


import java.util.Arrays;
import java.util.List;

public class Manager extends Worker
{
    // add permissions for managers
    private final List<Permission> permissions = Arrays.asList(
            Permission.ACCESS_SOURCE_REPORT,
            Permission.ACCESS_AVAILABILITY_REPORT,
            Permission.SUBMIT_REPORT,
            Permission.ACCESS_PURITY_REPORT,
            Permission.DELETE_REPORT,
            Permission.ACCESS_HISTORICAL_REPORT
    );

    @Override
    public boolean hasPermission(Permission permission) {
        if (permission == null) {
            throw new IllegalArgumentException("Cannot have null permission");
        }
        return this.permissions.contains(permission);
    }

    public String getType() {
        return "Manager";
    }
}
