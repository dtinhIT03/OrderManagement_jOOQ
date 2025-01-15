package org.example.ordermanagement_jooq.controller;

import org.example.ordermanagement_jooq.data.response.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public ResponseData<Boolean> register()
}
