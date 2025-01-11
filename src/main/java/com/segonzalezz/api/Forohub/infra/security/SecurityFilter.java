package com.segonzalezz.api.Forohub.infra.security;

import com.segonzalezz.api.Forohub.domain.user.DTO.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Get Token to Header
        var token = request.getHeader("Authorization");
        System.out.println(token);
        if(token!=null){
            System.out.println("Token is not null");
            token = token.substring(7);
            System.out.println(token);
            System.out.println(tokenService.getSubject(token));
            var subject = tokenService.getSubject(token);
            if (subject!=null){
                //Valid token
                var user = userRepository.findByEmail(subject);
                var authentication =  new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
