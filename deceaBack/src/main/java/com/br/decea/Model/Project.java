package com.br.decea.Model;

import com.br.decea.DTO.ProjectDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author wilson
 */
@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long zip_code;
    private double cost;
    private Boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_client",referencedColumnName = "id")
    @JsonBackReference
    private Client client;

    private Date deadline;
    private Date created_at;
    private Date updated_at;

    
}