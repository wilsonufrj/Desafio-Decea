package com.br.decea.ServiceImpl;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.UserDTO;
import com.br.decea.Model.User;
import com.br.decea.Model.Project;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.br.decea.Service.IUserService;
import com.br.decea.Repository.UserRepository;

/**
 *
 * @author wilson
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean login(UserDTO clientDTO) throws Exception {

        Optional<User> optClient = userRepository.findByUsername(clientDTO.getUsername());
        if (optClient.isEmpty()) {
            throw new Exception("Client not found");
        }

        User auxCLient = optClient.get();
        return validatePassword(clientDTO.getPassword(), auxCLient.getPassword());
    }

    @Override
    public UserDTO create(UserDTO clientDTO) {
        User auxUser = new User();
        auxUser.setName(clientDTO.getName());
        auxUser.setUsername(clientDTO.getUsername());
        auxUser.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        auxUser.setProjects(new ArrayList<Project>());

        User user = userRepository.save(auxUser);

        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    private Boolean validatePassword(String requestPassword, String findPassword) {
        return passwordEncoder.matches(requestPassword, findPassword);
    }

    @Override
    public User getUser(String username) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(()->new Exception("User not found"));
    }
}
