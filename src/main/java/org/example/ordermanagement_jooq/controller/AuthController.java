package org.example.ordermanagement_jooq.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.request.LoginRequest;
import org.example.ordermanagement_jooq.data.response.AuthenticationResponse;
import org.example.ordermanagement_jooq.data.response.ResponseData;
import org.example.ordermanagement_jooq.data.response.ResponseError;
import org.example.ordermanagement_jooq.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;
    @PostMapping("/register")
    public ResponseData<Boolean> register(@Valid @RequestBody AuthenticationRequest request){
        try{
            Boolean valid = authService.register(request);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "register user successfully!",valid);
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseData<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request){
        try{
            return new ResponseData<>(HttpStatus.OK.value(),"login user successfully!",authService.authenticate(request));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
