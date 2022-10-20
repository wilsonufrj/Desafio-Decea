package com.br.decea.data;

import com.br.decea.Model.Client;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author wilson
 */
public class DetailClientData implements UserDetails {
    
    
    private final Optional<Client> client;
    
    public DetailClientData(Optional<Client> client){
        this.client = client;
    }
   
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return client.orElse(new Client()).getPassword();
    }

    @Override
    public String getUsername() {
        return client.orElse(new Client()).getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
