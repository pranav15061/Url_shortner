package com.example.UrlShortner.service;


import com.example.UrlShortner.dto.LoginRequest;
import com.example.UrlShortner.email.TokenRepository;
import com.example.UrlShortner.model.User;
import com.example.UrlShortner.repository.UserRepository;
import com.example.UrlShortner.security.jwt.JwtAuthenticationResponse;
import com.example.UrlShortner.security.jwt.JwtUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;
    private  final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    private final  JwtUtils jwtUtils;





    public User registerUser(User user) throws MessagingException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);

    }





    public JwtAuthenticationResponse loginUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }

    public User findByUsername(String name) {
        return repository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + name)
        );
    }
}
