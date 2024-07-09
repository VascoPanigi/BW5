package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Role;
import team6.BW5.entities.User;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.payloads.RoleDTO.RoleAssignedDTO;
import team6.BW5.payloads.userDTO.UserDTO;
import team6.BW5.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCrypt;
    @Autowired
    private RoleService roleService;


    public Page<User> getUtenti(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public User saveUser(UserDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("The user with " + body.email() + " exist");
        });
        List<Role> roles = new ArrayList<>();
        Role found = this.roleService.findById(body.roleId());
        roles.add(found);


        User user = new User(body.username(), body.email(), bCrypt.encode(body.password()), body.name(), body.surname(), "https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname(), roles);


        return this.userRepository.save(user);

    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        this.userRepository.delete(found);
    }


    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User addRoles(UUID id, RoleAssignedDTO roleId) {
        System.out.println("CIAONE");
        User found = this.findById(id);
        System.out.println("CIAONE 2 ");
        Role role = this.roleService.findById(roleId.id());
        System.out.println(role);
        List<Role> roleList = found.getRolesList();
        System.out.println("CIAONE 3 ");
        System.out.println(role);

        roleList.add(role);
        System.out.println(roleList);

        found.setRolesList(roleList);

        return this.userRepository.save(found);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

}
