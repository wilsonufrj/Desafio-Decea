package com.br.decea.Service;

import com.br.decea.DTO.ClientDTORequest;
import com.br.decea.DTO.ClientDTOResponse;
import com.br.decea.Model.Client;
import java.util.List;

/**
 *
 * @author wilson
 */
public interface IClientService {
    public Boolean login(ClientDTORequest userDTO)throws Exception;
    public ClientDTOResponse create(ClientDTORequest userDTO);
    public List<Client> listUser();
}
