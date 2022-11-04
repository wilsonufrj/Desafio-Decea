package com.br.decea.Service;

import com.br.decea.DTO.UserDTO;
import com.br.decea.Model.User;
import java.util.List;

/**
 *
 * @author wilson
 */
public interface IUserService {
    public UserDTO login(UserDTO userDTO)throws Exception;
    public UserDTO create(UserDTO userDTO);
    public List<User> listUser();
    public User getUser(String username) throws Exception;
}
