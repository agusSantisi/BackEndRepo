
package com.portfolio.PortfolioBack.Controller;

import com.portfolio.PortfolioBack.Dto.dtoExperiencia;
import com.portfolio.PortfolioBack.Entity.Experiencia;
import com.portfolio.PortfolioBack.Security.Controller.Mensaje;
import com.portfolio.PortfolioBack.Service.SExperiencia;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = {"https://frontendportfoliosantisi.web.app", "http://localhost:4200"})
public class CExperiencia {
    @Autowired 
    SExperiencia Sexperiencia;
    
    @GetMapping("/list")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = Sexperiencia.list();
        return new ResponseEntity (list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!Sexperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = Sexperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoExp){
        if(StringUtils.isBlank(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoExp.getDescripcionE()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(Sexperiencia.existsByNombreE(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoExp.getNombreE(), dtoExp.getDescripcionE());
        Sexperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje ("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") int id, @RequestBody dtoExperiencia dtoExp){
        if(!Sexperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(Sexperiencia.existsByNombreE(dtoExp.getNombreE()) && Sexperiencia.getByNombreE(dtoExp.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio!!"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoExp.getDescripcionE()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = Sexperiencia.getOne(id).get();
        experiencia.setNombreE(dtoExp.getNombreE());
        experiencia.setDescripcionE(dtoExp.getDescripcionE());
        
        Sexperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("El item fue modificado correctamente"), HttpStatus.OK);
        
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
        
        if(!Sexperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        Sexperiencia.delete(id);
        return new ResponseEntity(new Mensaje("El item fue borrado correctamente"), HttpStatus.OK);
    }
    
    
    
    
    
    
}