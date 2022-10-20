package com.br.decea.ServiceImpl;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ProjectResponseDTO;
import com.br.decea.DTO.ProjectUpdateDTO;
import com.br.decea.External.Comunication;
import com.br.decea.External.Location;
import com.br.decea.Model.Client;
import com.br.decea.Model.Project;
import com.br.decea.Repository.ClientRepository;
import com.br.decea.Repository.ProjectRepository;
import com.br.decea.Service.IProjectService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author wilson
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Project create(ProjectDTO projectDTO) {

        Project auxProject = new Project();

        auxProject.setTitle(projectDTO.getTitle());
        auxProject.setZip_code(projectDTO.getZip_code());
        auxProject.setCost(projectDTO.getCost());
        auxProject.setDone(projectDTO.getDone());

        Optional<Client> optClient = clientRepository.findByUsername(projectDTO.getUsername());
        auxProject.setClient(optClient.get());

        auxProject.setDeadline(projectDTO.getDeadline());
        auxProject.setCreated_at(projectDTO.getCreated_at());
        auxProject.setUpdated_at(projectDTO.getUpdated_at());

        return projectRepository.save(auxProject);

    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
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
        projectResponseDTO.setUsername(auxProject.getClient().getUsername());
        projectResponseDTO.setDeadline(auxProject.getDeadline());
        projectResponseDTO.setCreated_at(auxProject.getCreated_at());
        projectResponseDTO.setUpdated_at(auxProject.getUpdated_at());

        return projectResponseDTO;
    }

    @Override
    public Project update(Long id, ProjectUpdateDTO projectDTO) throws Exception {
        Project auxProject = projectRepository.findById(id)
                .orElseThrow(() -> new Exception("Project not found"));

        if (auxProject.getDone()) {
            throw new Exception("Project had done");
        }

        auxProject.setTitle(projectDTO.getTitle());
        auxProject.setZip_code(projectDTO.getZip_code());
        auxProject.setDeadline(projectDTO.getDeadline());

        return projectRepository.save(auxProject);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void doneProject(Long id) throws Exception {
        Optional<Project> optProject = projectRepository.findById(id);

        if (optProject.isEmpty()) {
            throw new Exception("Project not found");
        }

        optProject.get().setDone(Boolean.TRUE);
        projectRepository.save(optProject.get());
    }

}
