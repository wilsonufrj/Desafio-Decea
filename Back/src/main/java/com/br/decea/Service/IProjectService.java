package com.br.decea.Service;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ProjectInfoDTO;
import com.br.decea.DTO.ProjectNameIdDTO;
import com.br.decea.DTO.ProjectUpdateDTO;
import com.br.decea.Model.Project;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author wilson
 */
public interface IProjectService {
    public ProjectDTO create(ProjectDTO projectDTO,String username) throws Exception;
    public List<Project> getAll(String username) throws Exception;
    public ProjectInfoDTO get(UUID id) throws Exception;
    public void delete(UUID id,String username)throws Exception;
    public void doneProject(UUID id,String username) throws Exception;
    public ProjectUpdateDTO update(UUID id, ProjectUpdateDTO projectDTO, String username) throws Exception;
}
