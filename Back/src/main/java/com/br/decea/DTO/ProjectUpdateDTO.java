package com.br.decea.DTO;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 *
 * @author wilson
 */
@Getter
@Setter
@AllArgsConstructor
public class ProjectUpdateDTO {
    
    private String title;
    private Long zip_code;
    private Date deadline;
    private double cost;
    private String city;
    private String state;
    private Date updated_at;

   
}
