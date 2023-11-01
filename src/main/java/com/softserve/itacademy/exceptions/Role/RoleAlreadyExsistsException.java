package com.softserve.itacademy.exceptions.Role;

public class RoleAlreadyExsistsException extends RuntimeException{
    private long roleId;

    public RoleAlreadyExsistsException(long roleId){
        super("The role with the ID " + roleId + " already exists.");
        this.roleId= roleId;
    }

    public long getRoleId(){
        return roleId;
    }
}
