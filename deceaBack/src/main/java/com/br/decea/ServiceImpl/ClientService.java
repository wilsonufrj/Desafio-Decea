package com.br.decea.ServiceImpl;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ClientDTORequest;
import com.br.decea.DTO.ClientDTOResponse;
import com.br.decea.Model.Client;
import com.br.decea.Model.Project;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.decea.Repository.ClientRepository;
import com.br.decea.Service.IClientService;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author wilson
 */
@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean login(ClientDTORequest clientDTO) throws Exception {
        
        Optional<Client> optClient = clientRepository.findByUsername(clientDTO.getUsername());
        if(optClient.isEmpty()){
            throw new Exception("Client not found");
        }
        
        Client auxCLient = optClient.get();
        return passwordEncoder.matches(clientDTO.getPassword(), auxCLient.getPassword());
    }

    @Override
    public ClientDTOResponse create(ClientDTORequest clientDTO) {
        Client auxClient = new Client();
        auxClient.setName(clientDTO.getName());
        auxClient.setUsername(clientDTO.getUsername());
        auxClient.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        auxClient.setProjects(new ArrayList<Project>());
        
        Client clientSaved = clientRepository.save(auxClient);

        return new ClientDTOResponse(clientSaved.getId(), clientSaved.getName(),clientSaved.getUsername(), clientSaved.getProjects());
    }

    @Override
    public List<Client> listUser() {
        return clientRepository.findAll();
    }

}
