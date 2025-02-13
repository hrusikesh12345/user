package com.example.RegisterLogin.Service;
import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Dto.UserDTO;
import com.example.RegisterLogin.payload.response.LoginMesage;
public interface UserService {
    String addUser(UserDTO userDTO);
    LoginMesage loginUser(LoginDTO loginDTO);

}