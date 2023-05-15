
package com.portfolio.PortfolioBack.Security.Service;

import com.portfolio.PortfolioBack.Security.Entity.Rol;
import com.portfolio.PortfolioBack.Security.Enums.RolNombre;
import com.portfolio.PortfolioBack.Security.Repository.iRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolnombre){
        return irolRepository.findByRolNombre(rolnombre);
    }
     
    public void save(Rol rol){
        irolRepository.save(rol);
    }
    
   
}
