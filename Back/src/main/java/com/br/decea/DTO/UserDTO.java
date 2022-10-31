package com.br.decea.DTO;

import com.br.decea.Model.Project;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author wilson
 */

@Getter
@Setter
public class UserDTO {
    private String name;
    private String username;
    private String password;
    private List<Project> projects;

 
}
