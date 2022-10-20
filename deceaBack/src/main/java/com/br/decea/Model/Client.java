package com.br.decea.Model;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ClientDTORequest;
import com.br.decea.DTO.ClientDTOResponse;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author wilson
 */
@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(unique=true)
    private String username;
    
    private String password;
    
    @OneToMany(
            mappedBy = "client",
            targetEntity = Project.class,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Project> projects;
    
}
