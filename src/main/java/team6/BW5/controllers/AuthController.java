package team6.BW5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team6.BW5.exceptions.BadRequestException;
import team6.BW5.payloads.userDTO.NewUserResponseDTO;
import team6.BW5.payloads.userDTO.UserDTO;
import team6.BW5.payloads.userDTO.UserLoginDTO;
import team6.BW5.payloads.userDTO.UserLoginResponseDTO;
import team6.BW5.services.AuthService;
import team6.BW5.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new UserLoginResponseDTO(authService.authenticateUtenteAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO saveUtenti(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new NewUserResponseDTO(this.userService.saveUser(body).getId());
    }
}
