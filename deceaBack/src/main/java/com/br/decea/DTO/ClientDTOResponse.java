package com.br.decea.DTO;

import com.br.decea.Model.Project;
import java.util.List;

/**
 *
 * @author wilson
 */
public class ClientDTOResponse {
     private Long id;
    private String name;
    private String username;
    private List<Project> projects;

    public ClientDTOResponse(Long id, String name, String username, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
        
    
}
