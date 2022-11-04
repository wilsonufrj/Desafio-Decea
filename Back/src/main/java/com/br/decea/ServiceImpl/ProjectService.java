package com.br.decea.ServiceImpl;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ProjectInfoDTO;
import com.br.decea.DTO.ProjectNameIdDTO;
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
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectDTO create(ProjectDTO projectDTO,String username) throws Exception {

        try {
            User user = userService.getUser(username);

            Project auxProject = new Project();
            auxProject.setUser(user);
            auxProject.setTitle(projectDTO.getTitle());
            auxProject.setZip_code(projectDTO.getZip_code());
            auxProject.setCost(projectDTO.getCost());
            auxProject.setDone(projectDTO.getDone());

            auxProject.setDeadline(projectDTO.getDeadline());
            auxProject.setCreated_at(projectDTO.getCreated_at());
            auxProject.setUpdated_at(projectDTO.getUpdated_at());

            return modelMapper.map(projectRepository.save(auxProject),ProjectDTO.class);
        } catch (Exception e) {
            throw new Exception("User not found");
        }
    }

    @Override
    public List<ProjectNameIdDTO> getAll(String username) throws Exception {
        try {
            User user = userService.getUser(username);
            return user.getProjects()
                    .stream()
                    .map(project->this.modelMapper.map(project,ProjectNameIdDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("User not found");
        }
    }

    @Override
    public ProjectInfoDTO get(Long id) throws Exception {

        Project auxProject = projectRepository.findById(id)
                .orElseThrow(() -> new Exception("Project not found"));

        Comunication comunication = new Comunication(auxProject.getZip_code());
        Location location = comunication.getData();

        ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
        projectInfoDTO.setCity(location.getCity());
        projectInfoDTO.setState(location.getState());
        projectInfoDTO.setId(auxProject.getId());
        projectInfoDTO.setTitle(auxProject.getTitle());
        projectInfoDTO.setCost(auxProject.getCost());
        projectInfoDTO.setDone(auxProject.getDone());
        projectInfoDTO.setUsername(auxProject.getUser().getUsername());
        projectInfoDTO.setDeadline(auxProject.getDeadline());
        projectInfoDTO.setCreated_at(auxProject.getCreated_at());
        projectInfoDTO.setUpdated_at(auxProject.getUpdated_at());

        return projectInfoDTO;
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
