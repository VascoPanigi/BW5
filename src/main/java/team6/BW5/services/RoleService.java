package team6.BW5.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Role;
import team6.BW5.enums.UserRoles;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.payloads.RoleDTO.NewRoleDTO;
import team6.BW5.repositories.RoleRepository;

import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(NewRoleDTO body) {
        Role user = new Role(convertUserRoleToStr(body.effectiveRole()));
        return this.roleRepository.save(user);
    }

    public Role findById(UUID id) {
        return this.roleRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Role findByRoleName(UserRoles roleName) {
        return this.roleRepository.findByEffectiveRole(roleName).orElseThrow(() -> new NotFoundException("nessun parametro torvato"));
    }

        public static UserRoles convertUserRoleToStr(String userRole){
        try {
            return UserRoles.valueOf(userRole.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid user role: " + userRole + ". Choose between USER, ADMIN. Exception " + e);
        }
    }
}
