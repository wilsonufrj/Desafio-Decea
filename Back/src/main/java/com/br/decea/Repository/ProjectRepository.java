package com.br.decea.Repository;

import com.br.decea.Model.Project;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wilson
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID>{
    
}
