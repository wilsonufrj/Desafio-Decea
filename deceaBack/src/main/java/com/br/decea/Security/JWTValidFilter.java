package com.br.decea.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author wilson
 */
public class JWTValidFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String PREFIX_ATTRIBUTE = "Bearer ";

    public JWTValidFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String attribute = request.getHeader(HEADER_ATTRIBUTE);
        
        if (attribute == null || !attribute.startsWith(PREFIX_ATTRIBUTE) ) {
            chain.doFilter(request, response);
            return;
        }
        
        String token = attribute.replace(PREFIX_ATTRIBUTE, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private  UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String client = JWT.require(Algorithm.HMAC512(JWTAutheticationFilter.TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();
        
        if( client == null){
            return null;
        }
        
        return new UsernamePasswordAuthenticationToken(client, null, new ArrayList<>());
        
    }
    
}
