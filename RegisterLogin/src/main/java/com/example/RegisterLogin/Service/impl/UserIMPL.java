package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Repo.UserRepo;
import com.example.RegisterLogin.Service.UserService;
import com.example.RegisterLogin.payload.response.LoginMesage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import javax.annotation.Resource;
import java.util.Optional;
@Service
public class UserIMPL implements UserService {
    @Autowired
    private UserRepo UserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addUser(UserDTO userDTO) {

        User existingUser = UserRepo.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            return "Email already exists!";
        }

        User user = new User(
                userDTO.getUserid(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        UserRepo.save(user);
        return user.getEmail();
    }
    UserDTO userDTO;
    @Override
    public LoginMesage  loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = UserRepo.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = UserRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMesage("Login Success", true);
                } else {
                    return new LoginMesage("Login Failed", false);
                }
            } else {
                return new LoginMesage("Password Not Match", false);
            }
        }else {
            return new LoginMesage("Email not exits", false);
        }
    }
}