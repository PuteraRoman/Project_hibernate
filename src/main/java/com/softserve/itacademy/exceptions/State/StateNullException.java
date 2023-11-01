package com.softserve.itacademy.exceptions.State;

public class StateNullException extends RuntimeException{
    public StateNullException(){
        super("The state object is null.");
    }
}
