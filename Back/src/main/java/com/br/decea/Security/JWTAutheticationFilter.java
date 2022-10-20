package com.br.decea.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.decea.Model.Client;
import com.br.decea.data.DetailClientData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author wilson
 */
public class JWTAutheticationFilter extends UsernamePasswordAuthenticationFilter {
    
    public static final int TOKEN_EXPIRACAO = 600_000;
    
    public static final String TOKEN_SENHA = "37f87342-4077-46e1-8019-6450c566d004";
    
   
    private final AuthenticationManager authenticationManager;

    public JWTAutheticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        
        try {
            Client client = new ObjectMapper().readValue(request.getInputStream(), Client.class);
            
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               client.getUsername(),
               client.getPassword(),
               new ArrayList<>()
            ));
            
        } catch (IOException ex) {
            throw new RuntimeException("Failed to authenticate the client",ex);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request,HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
        
        DetailClientData clientData = (DetailClientData) authResult.getPrincipal();
        
        String token = JWT.create()
                .withSubject(clientData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));
                
        response.getWriter().write(token);
        response.getWriter().flush();
        
    }
    
}
