package team6.BW5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team6.BW5.entities.User;
import team6.BW5.payloads.RoleDTO.RoleAssignedDTO;
import team6.BW5.services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/{userId}")
    private User findById(@PathVariable UUID userId) {
        return this.userService.findById(userId);
    }

    @PatchMapping("/{userId}")
    private User AddRole(@PathVariable UUID userId, @RequestBody RoleAssignedDTO payload) {
        return this.userService.addRoles(userId, payload);
    }
}
