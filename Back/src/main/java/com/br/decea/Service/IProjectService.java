package com.br.decea.Service;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ProjectResponseDTO;
import com.br.decea.DTO.ProjectUpdateDTO;
import com.br.decea.Model.Project;
import java.util.List;

/**
 *
 * @author wilson
 */
public interface IProjectService {
    public Project create(ProjectDTO projectDTO);
    public List<Project> getAll();
    public ProjectResponseDTO get(Long id) throws Exception;
    public void delete(Long id);
    public void doneProject(Long id) throws Exception;
    public Project update(Long id, ProjectUpdateDTO projectDTO) throws Exception;
}
