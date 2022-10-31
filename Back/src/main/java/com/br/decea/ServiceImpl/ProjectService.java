package com.br.decea.ServiceImpl;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ProjectResponseDTO;
import com.br.decea.DTO.ProjectUpdateDTO;
import com.br.decea.External.Comunication;
import com.br.decea.External.Location;
import com.br.decea.Model.User;
import com.br.decea.Model.Project;
import com.br.decea.Repository.ProjectRepository;
import com.br.decea.Service.IProjectService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.decea.Repository.UserRepository;
import com.br.decea.Service.IUserService;

/**
 *
 * @author wilson
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IUserService userService;

    @Override
    public Project create(ProjectDTO projectDTO) throws Exception {

        try {
            User user = userService.getUser(projectDTO.getUsername());

            Project auxProject = new Project();
            auxProject.setUser(user);
            auxProject.setTitle(projectDTO.getTitle());
            auxProject.setZip_code(projectDTO.getZip_code());
            auxProject.setCost(projectDTO.getCost());
            auxProject.setDone(projectDTO.getDone());

            auxProject.setDeadline(projectDTO.getDeadline());
            auxProject.setCreated_at(projectDTO.getCreated_at());
            auxProject.setUpdated_at(projectDTO.getUpdated_at());

            return projectRepository.save(auxProject);
        } catch (Exception e) {
            throw new Exception("User not found");
        }
    }

    @Override
    public List<Project> getAll(String username) throws Exception {
        try {
            User user = userService.getUser(username);
            return user.getProjects();
        } catch (Exception e) {
            throw new Exception("User not found");
        }
    }

    @Override
    public ProjectResponseDTO get(Long id) throws Exception {

        Project auxProject = projectRepository.findById(id)
                .orElseThrow(() -> new Exception("Project not found"));

        Comunication comunication = new Comunication(auxProject.getZip_code());
        Location location = comunication.getData();

        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setCity(location.getCity());
        projectResponseDTO.setState(location.getState());
        projectResponseDTO.setId(auxProject.getId());
        projectResponseDTO.setTitle(auxProject.getTitle());
        projectResponseDTO.setCost(auxProject.getCost());
        projectResponseDTO.setDone(auxProject.getDone());
        projectResponseDTO.setUsername(auxProject.getUser().getUsername());
        projectResponseDTO.setDeadline(auxProject.getDeadline());
        projectResponseDTO.setCreated_at(auxProject.getCreated_at());
        projectResponseDTO.setUpdated_at(auxProject.getUpdated_at());

        return projectResponseDTO;
    }

    @Override
    public Project update(Long id, ProjectUpdateDTO projectDTO, String username) throws Exception {
        Project auxProject = projectRepository.findById(id)
                .orElseThrow(() -> new Exception("Project not found"));

        if (auxProject.getDone()) {
            throw new Exception("Project had done");
        }

        if (auxProject.getUser().getUsername() != username) {
            throw new Exception("The user can't do any alteration");
        }

        auxProject.setTitle(projectDTO.getTitle());
        auxProject.setZip_code(projectDTO.getZip_code());
        auxProject.setDeadline(projectDTO.getDeadline());

        return projectRepository.save(auxProject);
    }

    @Override
    public void delete(Long id,String username) throws Exception {
        Project project = projectRepository.findById(id).orElseThrow(
        ()-> new Exception("Project not found"));
        
        if (project.getUser().getUsername() != username) {
            throw new Exception("The user can't do any alteration");
        }
        
        projectRepository.deleteById(id);
    }

    @Override
    public void doneProject(Long id, String username) throws Exception {

        Project project = projectRepository.findById(id).orElseThrow(
                () -> new Exception("Project not found"));

        if (project.getUser().getUsername() != username) {
            throw new Exception("The user can't do any alteration");
        }

        project.setDone(Boolean.TRUE);
        projectRepository.save(project);
    }

}
