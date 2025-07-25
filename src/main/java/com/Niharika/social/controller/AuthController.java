package com.Niharika.social.controller;

import com.Niharika.social.config.JwtProvider;
import com.Niharika.social.models.User;
import com.Niharika.social.repository.UserRepository;
import com.Niharika.social.request.LoginRequest;
import com.Niharika.social.response.AuthResponse;
import com.Niharika.social.service.CustomUserDetailService;
import com.Niharika.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
   private UserService userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

//        User savedUser= userService.RegisterUser(user);
//
//        return savedUser;

        User isExists = userRepository.findByEmail(user.getEmail());

        if(isExists!=null){
            throw new Exception("email already used with another account");
        }
        User newUser =new User();


        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepository.save(newUser);

        Authentication authentication= new
                UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"Registration successful");
        return res;

    }


    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){

        Authentication authentication =
                authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"login successful");
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if(userDetails == null){
            throw new BadCredentialsException("Username invalid");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("password not matching..");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
