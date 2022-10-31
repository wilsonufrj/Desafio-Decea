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
    public Project create(ProjectDTO projectDTO) throws Exception;
    public List<Project> getAll(String username) throws Exception;
    public ProjectResponseDTO get(Long id) throws Exception;
    public void delete(Long id,String username)throws Exception;
    public void doneProject(Long id,String username) throws Exception;
    public Project update(Long id, ProjectUpdateDTO projectDTO, String username) throws Exception;
}
