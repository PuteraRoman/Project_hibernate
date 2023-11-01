package com.softserve.itacademy.exceptions.State;

public class StateNotFoundException extends RuntimeException{
    private long stateId;
    private String stateName;
    public StateNotFoundException(long stateId){
        super("State with ID " + stateId + " is not found");
        this.stateId = stateId;
    }

    public StateNotFoundException(String stateName){
        super("State with name " + stateName + " is not found");
        this.stateName = stateName;
    }
    public long getStateId(){
        return this.stateId;
    }
    public String getStateName(){
        return this.stateName;
    }
}
