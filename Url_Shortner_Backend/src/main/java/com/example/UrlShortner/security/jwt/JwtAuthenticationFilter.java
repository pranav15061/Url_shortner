package com.example.UrlShortner.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try
        {
            // Get JWT From Header
             //Validate Token
            // If Valid Get User details
            // get User name -> load User -> Set the auth context

            String jwt = jwtUtils.getJwtFromHeader(request);

            if(jwt!=null && jwtUtils.validateToken(jwt))
            {
                String username=jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails=userDetailsService.loadUserByUsername(username);

                if (userDetails != null){
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }
}
