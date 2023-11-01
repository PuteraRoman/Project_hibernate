package com.softserve.itacademy.exceptions.Role;

public class RoleNullException extends RuntimeException{
    public RoleNullException(){
        super("The role object is null.");
    }
}
