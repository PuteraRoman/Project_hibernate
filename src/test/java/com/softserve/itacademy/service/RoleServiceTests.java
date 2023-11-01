package com.softserve.itacademy.service;
import com.softserve.itacademy.exceptions.Role.RoleNotFoundException;
import com.softserve.itacademy.exceptions.Role.RoleNullException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import com.softserve.itacademy.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class RoleServiceTests {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    void testCreateRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");

        when(roleRepository.findById(role.getId())).thenReturn(Optional.empty());
        when(roleRepository.save(role)).thenReturn(role);

        Role createdRole = roleService.create(role);

        assertNotNull(createdRole);
        assertEquals(role.getName(), createdRole.getName());

        verify(roleRepository, times(1)).findById(role.getId());
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void testReadByIdExistingRole() {
        long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);
        role.setName("Admin");

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        Role foundRole = roleService.readById(roleId);

        assertNotNull(foundRole);
        assertEquals(role.getName(), foundRole.getName());


    }

    @Test
    void testReadByIdNonExistingRole() {
        long roleId = 1L;

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> roleService.readById(roleId));

        verify(roleRepository, times(1)).findById(roleId);
    }

    @Test
    void testUpdateRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");

        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
        when(roleRepository.save(role)).thenReturn(role);

        Role updatedRole = roleService.update(role);

        assertNotNull(updatedRole);
        assertEquals(role.getName(), updatedRole.getName());

        verify(roleRepository, times(1)).findById(role.getId());
        verify(roleRepository, times(1)).save(role);
    }



    @Test
    void testDeleteNonExistingRole() {
        long roleId = 1L;

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> roleService.delete(roleId));

        verify(roleRepository, times(1)).findById(roleId);
        verify(roleRepository, never()).delete(any(Role.class));
    }

    @Test
    void testGetAllRoles() {
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("Admin");
        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("User");
        roles.add(role1);
        roles.add(role2);

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> allRoles = roleService.getAll();

        assertNotNull(allRoles);
        assertEquals(2, allRoles.size());

        verify(roleRepository, times(1)).findAll();
    }
}
