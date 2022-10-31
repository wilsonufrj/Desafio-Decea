package com.br.decea.DTO;

import com.br.decea.Model.User;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author wilson
 */
@Getter
@Setter
public class ProjectDTO {
    
    private Long id;
    private String title;
    private Long zip_code;
    private double cost;
    private Boolean done;
    private String username;
    private Date deadline;
    private Date created_at;
    private Date updated_at;

  
}
