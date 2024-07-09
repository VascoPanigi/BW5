package team6.BW5.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team6.BW5.entities.User;
import team6.BW5.exceptions.UnauthorizedException;
import team6.BW5.payloads.userDTO.UserLoginDTO;
import team6.BW5.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bCrypt;

    public String authenticateUtenteAndGenerateToken(UserLoginDTO payload) {
        User dipendenti = this.userService.findByEmail(payload.email());
        if (bCrypt.matches(payload.password(), dipendenti.getPassword())) {
            return jwtTools.createToken(dipendenti);
        } else {
            throw new UnauthorizedException("Credenziali non corrette");
        }
    }
}
