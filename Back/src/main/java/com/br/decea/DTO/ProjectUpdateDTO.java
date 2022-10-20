package com.br.decea.DTO;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author wilson
 */
@Getter
@Setter
public class ProjectUpdateDTO {
    
    private String title;
    private Long zip_code;
    private Date deadline;

   
}
