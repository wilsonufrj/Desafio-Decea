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
public class ProjectResponseDTO {
    private Long id;
    private String title;
    private String city;
    private String state;
    private double cost;
    private Boolean done;
    private String username;
    private Date deadline;
    private Date created_at;
    private Date updated_at;
}
