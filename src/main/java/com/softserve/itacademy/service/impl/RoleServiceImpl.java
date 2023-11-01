package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exceptions.Role.RoleAlreadyExsistsException;
import com.softserve.itacademy.exceptions.Role.RoleNotFoundException;
import com.softserve.itacademy.exceptions.Role.RoleNullException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Autowired
   public RoleServiceImpl(RoleRepository roleRepository){
       this.roleRepository = roleRepository;
   }

    @Override
    public Role create(Role role) {
            if (role == null){
                throw new RoleNullException();
            }
            if (roleRepository.findById(role.getId()).isPresent()){
                throw new RoleAlreadyExsistsException(role.getId());
            }
                return roleRepository.save(role);

    }

    @Override
    public Role readById(long id) {
      if (!roleRepository.findById(id).isPresent()){
          throw new RoleNotFoundException(id);
      }
      return roleRepository.findById(id).get();
    }

    @Override
    public Role update(Role role) {
        if(role == null){
           throw new RoleNullException();
        }
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        if(!optionalRole.isPresent()){
            throw new RoleNotFoundException(role.getId());
        }
        Role result = optionalRole.get();
        result.setName(role.getName());
        result.setUsers(role.getUsers());
       return roleRepository.save(result);
    }

    @Override
    public void delete(long id) {
        if (!roleRepository.findById(id).isPresent()){
            throw new RoleNotFoundException(id);
        }
        Role result = roleRepository.findById(id).get();
        roleRepository.delete(result);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
