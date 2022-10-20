package com.br.decea.Repository;

import com.br.decea.Model.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wilson
 */
@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
    
    public Optional<Client> findByUsername(String username);
    
}
