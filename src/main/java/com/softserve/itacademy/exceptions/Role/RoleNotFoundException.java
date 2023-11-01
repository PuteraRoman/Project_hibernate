package com.softserve.itacademy.exceptions.Role;

public class RoleNotFoundException extends RuntimeException{
    private long roleId;
    public RoleNotFoundException(long roleId){
        super("Role with ID " + roleId + " is not found");
        this.roleId = roleId;
    }
    public long getRoleId(){
        return this.roleId;
    }
}
