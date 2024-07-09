package team6.BW5.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Role;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.payloads.RoleDTO.NewRoleDTO;
import team6.BW5.repositories.RoleRepository;

import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(NewRoleDTO body) {


        Role user = new Role(body.effectiveRole());


        return this.roleRepository.save(user);

    }

    public Role findById(UUID id) {
        return this.roleRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
}
