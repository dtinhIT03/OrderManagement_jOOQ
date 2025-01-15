package org.example.ordermanagement_jooq.controller;

import jakarta.validation.Valid;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.response.ResponseData;
import org.example.ordermanagement_jooq.data.response.ResponseError;
import org.example.ordermanagement_jooq.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    AuthService authService;
    @PostMapping
    public ResponseData<Boolean> register(@Valid @RequestParam AuthenticationRequest request){
        try{
            Boolean valid = authService.register(request);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "register user successfully!",valid);
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
