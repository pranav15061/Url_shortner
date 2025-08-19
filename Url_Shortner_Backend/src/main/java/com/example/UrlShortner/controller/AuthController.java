package com.example.UrlShortner.controller;



import com.example.UrlShortner.dto.LoginRequest;
import com.example.UrlShortner.dto.RegisterRequest;
import com.example.UrlShortner.model.User;
import com.example.UrlShortner.security.jwt.JwtAuthenticationResponse;
import com.example.UrlShortner.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return  ResponseEntity.ok(userService.loginUser(loginRequest));
    }


    @PostMapping("/public/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) throws MessagingException {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setRole("ROLE_USER");
        User savedUser=userService.registerUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }


}
