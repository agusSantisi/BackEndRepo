
package com.portfolio.PortfolioBack.Controller;

import com.portfolio.PortfolioBack.Dto.dtoEducacion;
import com.portfolio.PortfolioBack.Entity.Educacion;
import com.portfolio.PortfolioBack.Security.Controller.Mensaje;
import com.portfolio.PortfolioBack.Service.SEducacion;

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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://frontendportfoliosantisi.web.app")
public class CEducacion {
    @Autowired 
    SEducacion Seducacion;
    
    @GetMapping("/list")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = Seducacion.list();
        return new ResponseEntity (list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!Seducacion.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = Seducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoEdu){
        if(StringUtils.isBlank(dtoEdu.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(Seducacion.existsByNombreE(dtoEdu.getNombreE()))
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion(dtoEdu.getNombreE(), dtoEdu.getDescripcionE());
        Seducacion.save(educacion);
        return new ResponseEntity(new Mensaje ("Educacion agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") int id, @RequestBody dtoEducacion dtoEdu){
        if(!Seducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(Seducacion.existsByNombreE(dtoEdu.getNombreE()) && Seducacion.getByNombreE(dtoEdu.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese item de educación ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoEdu.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio!!"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = Seducacion.getOne(id).get();
        educacion.setNombreE(dtoEdu.getNombreE());
        educacion.setDescripcionE(dtoEdu.getDescripcionE());
        Seducacion.save(educacion);
        return new ResponseEntity(new Mensaje("El item fue modificado correctamente"), HttpStatus.OK);
        
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
        
        if(!Seducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        Seducacion.delete(id);
        return new ResponseEntity(new Mensaje("El item fue borrado correctamente"), HttpStatus.OK);
    }
    
    
    
    
    
    
}