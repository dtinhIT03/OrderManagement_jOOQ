package org.example.ordermanagement_jooq.service.impl;

import generated_sources.tables.pojos.User;
import org.example.ordermanagement_jooq.data.mapper.UserMapper;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.response.AuthenticationResponse;
import org.example.ordermanagement_jooq.exception.EmailAlreadyExistException;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.example.ordermanagement_jooq.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    UserMapper userMapper;
    @Override
    public boolean register(AuthenticationRequest request) {
        //kiem tra mail da ton tai chua
        if(!userRepository.exitsByMail(request.getMail())){
            User user = userMapper.toUser(request);
            userRepository.save(user);
            return true;
        }else {
            throw new EmailAlreadyExistException("email is existed!");
        }

    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        return null;
    }

    @Override
    public String logout(String token) {
        return null;
    }
}
