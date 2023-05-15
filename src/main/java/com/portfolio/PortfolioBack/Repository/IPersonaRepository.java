
package com.portfolio.PortfolioBack.Repository;

import com.portfolio.PortfolioBack.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Contiene todos los métodos necesarios para el CRUD, principalmente.
@Repository 
public interface IPersonaRepository extends JpaRepository<Persona, Long>{
    
}
