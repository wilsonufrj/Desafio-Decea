/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.decea.ServiceImpl;

import com.br.decea.Model.Client;
import com.br.decea.Repository.ClientRepository;
import com.br.decea.data.DetailClientData;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author wilson
 */
@Component
public class DetailClientService implements UserDetailsService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByUsername(username);
        if(client.isEmpty()){
            throw new UsernameNotFoundException("User [" + username +" ] not found");
        }
        
        return new DetailClientData(client);
    }
    
}
