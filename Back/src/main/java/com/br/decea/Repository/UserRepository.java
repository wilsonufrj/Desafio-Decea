package com.br.decea.Repository;

import com.br.decea.Model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wilson
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    
    public Optional<User> findByUsername(String username);
    
}
