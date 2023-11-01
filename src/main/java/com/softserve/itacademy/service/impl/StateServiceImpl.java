package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exceptions.Role.RoleNotFoundException;
import com.softserve.itacademy.exceptions.State.StateAlreadyExistsException;
import com.softserve.itacademy.exceptions.State.StateNotFoundException;
import com.softserve.itacademy.exceptions.State.StateNullException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    private StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository){
        this.stateRepository = stateRepository;
    }
    @Override
    public State create(State state) {
        if (state == null){
            throw new StateNullException();
        }
        if (stateRepository.findById(state.getId()).isPresent()){
            throw new StateAlreadyExistsException(state.getId());
        }
       return stateRepository.save(state);
    }

    @Override
    public State readById(long id) {
        if(!stateRepository.findById(id).isPresent()){
            throw new StateNotFoundException(id);
        }
        return stateRepository.findById(id).get();
    }

    @Override
    public State update(State state) {
       if(state == null) {
           throw new StateNullException();
       }
       Optional<State> stateOptional = stateRepository.findById(state.getId());
       if (!stateOptional.isPresent()){
           throw new StateNotFoundException(state.getId());
       }
       State result = stateOptional.get();
       result.setName(state.getName());
       result.setTasks(state.getTasks());
       return stateRepository.save(result);
    }

    @Override
    public void delete(long id) {
        Optional<State> optionalState = stateRepository.findById(id);
        if (optionalState.isEmpty()) {
            throw new StateNotFoundException(id);
        }
        State result = optionalState.get();
        stateRepository.delete(result);
    }
    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getByName(String name) {
        State state = stateRepository.getByName(name);
        if(state == null){
            throw new StateNotFoundException(name);
        }
        return state;
    }

    @Override
    public List<State> getSortAsc() {
        return stateRepository.getSortAsc();
    }
}
