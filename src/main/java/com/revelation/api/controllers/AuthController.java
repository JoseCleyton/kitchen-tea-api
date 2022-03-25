package com.revelation.api.controllers;

import com.revelation.api.dtos.CredentialsDto;
import com.revelation.api.dtos.TokenDTO;
import com.revelation.api.dtos.UserDto;
import com.revelation.api.exceptions.PasswordInvalidException;
import com.revelation.api.models.UserModel;
import com.revelation.api.services.JwtService;
import com.revelation.api.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserModel> authenticate(@RequestBody @Valid UserDto userDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.ok(this.userService.save(userModel));
    }

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody CredentialsDto credentialsDTO)
            throws UsernameNotFoundException {
        try {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(credentialsDTO, userModel);
            UserModel userAutheticated = this.userService.authenticate(userModel);
            String token = this.jwtService.generatedTokenWithExpirationSendEmail(userAutheticated.getLogin(),
                    "1440");
            return ResponseEntity.ok(new TokenDTO(userAutheticated.getLogin(), token, userAutheticated.isAdmin()));

        } catch (UsernameNotFoundException | PasswordInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


}
