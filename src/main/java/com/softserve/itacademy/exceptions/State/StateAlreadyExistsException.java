package com.softserve.itacademy.exceptions.State;

public class StateAlreadyExistsException extends RuntimeException{
    private long stateId;
    public StateAlreadyExistsException(long stateId){
        super("State with ID " + stateId + " already exists");
        this.stateId = stateId;
    }
    public long getStateId(){
        return stateId;
    }
}
