
package com.portfolio.PortfolioBack.Service;

import com.portfolio.PortfolioBack.Entity.Educacion;
import com.portfolio.PortfolioBack.Entity.Proyecto;
import com.portfolio.PortfolioBack.Repository.RProyecto;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProyecto {
    @Autowired
    RProyecto Rproyecto;
    
    public List<Proyecto> list(){
        return Rproyecto.findAll();
    }
    
    public Optional<Proyecto> getOne(int id){
        return Rproyecto.findById(id);
    }
    
    public Optional<Proyecto> getByNombreP(String nombreP){
        return Rproyecto.findByNombreP(nombreP);
    }
    
    public void save(Proyecto pro){
        Rproyecto.save(pro);
    }
    
    public void delete(int id){
        Rproyecto.deleteById(id);
    }
    
    public boolean existsById(int id){
        return Rproyecto.existsById(id);
    }
    
    public boolean existsByNombreP(String nombreP){
        return Rproyecto.existsByNombreP(nombreP);
    }
}
