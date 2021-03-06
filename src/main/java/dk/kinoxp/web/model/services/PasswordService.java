package dk.kinoxp.web.model.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordService {

    PasswordEncoder encoder;

    public PasswordService(){
        encoder = new BCryptPasswordEncoder(10);
    }

    public String encodePassword(String password){
        return encoder.encode(password);
    }

    public boolean checkMatch(String plainPassword, String hashedPassword){
        return (encoder.matches(plainPassword, hashedPassword));

    }
}
