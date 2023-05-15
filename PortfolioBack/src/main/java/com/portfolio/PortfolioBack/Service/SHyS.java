
package com.portfolio.PortfolioBack.Service;

import com.portfolio.PortfolioBack.Entity.HyS;
import com.portfolio.PortfolioBack.Repository.RHyS;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHyS {
    @Autowired
    RHyS rhys;
    
    public List<HyS> list(){
        return rhys.findAll();
    }
    
    public Optional<HyS> getOne(int id){
        return rhys.findById(id);
    }
    
    public Optional<HyS> getByTecnologia(String tecnologia){
        return rhys.findByTecnologia(tecnologia);
    }
        
    public void save(HyS skill){
        rhys.save(skill);
    }
    
    public void delete(int id){
        rhys.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rhys.existsById(id);
    }
    
    public boolean existsByTecnologia(String tecnologia){
        return rhys.existsByTecnologia(tecnologia);
    }
}
