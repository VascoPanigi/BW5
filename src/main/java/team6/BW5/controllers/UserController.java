package team6.BW5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team6.BW5.entities.User;
import team6.BW5.payloads.RoleDTO.RoleAssignedDTO;
import team6.BW5.payloads.userDTO.UserDTO;
import team6.BW5.services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")

    public User findById(@PathVariable UUID userId) {
        System.out.println(this.userService.findById(userId).getAuthorities().toString());
        return this.userService.findById(userId);
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User AddRole(@PathVariable UUID userId, @RequestBody RoleAssignedDTO payload) {
        return this.userService.addRoles(userId, payload);

    }

    @GetMapping
//    @PreAuthorize("hasAuthority('admin')")
    public Page<User> getUsersList(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortedBy) {
        return userService.getAllUsers(page, size, sortedBy);

    }

    @DeleteMapping("/{userId}")
//    @PreAuthorize("hasAuthority('admin')")
    public void deleteUserProfile(@PathVariable UUID userId) {
        userService.findByIdAndDelete(userId);
    }

    @GetMapping("/me")
    public User getOwnProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return userService.findById(currentAuthenticatedUser.getId());
    }

    @PutMapping("/me")
    public User updateOwnProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody UserDTO payload) {
        return userService.findByIdAndUpdate(currentAuthenticatedUser.getId(), payload);
    }

    @DeleteMapping("/me")
    public void deleteOwnProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        userService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }

}
