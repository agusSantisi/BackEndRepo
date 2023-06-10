
package com.portfolio.PortfolioBack.Controller;

import com.portfolio.PortfolioBack.Dto.dtoProyecto;
import com.portfolio.PortfolioBack.Entity.Proyecto;
import com.portfolio.PortfolioBack.Security.Controller.Mensaje;
import com.portfolio.PortfolioBack.Service.SProyecto;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontendportfoliosantisi.web.app", "http://localhost:4200"})
public class CProyecto {
    @Autowired
    SProyecto Sproyecto;
    
    @GetMapping("/list")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = Sproyecto.list();
        return new ResponseEntity (list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!Sproyecto.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = Sproyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoPro){
        if(StringUtils.isBlank(dtoPro.getNombreP()))
            return new ResponseEntity(new Mensaje("¡El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
        if(Sproyecto.existsByNombreP(dtoPro.getNombreP()))
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe..."), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = new Proyecto(dtoPro.getNombreP(), dtoPro.getDescripcionP());
        Sproyecto.save(proyecto);
        return new ResponseEntity(new Mensaje ("Proyecto agregado"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") int id, @RequestBody dtoProyecto dtoPro){
        if(!Sproyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(Sproyecto.existsByNombreP(dtoPro.getNombreP()) && Sproyecto.getByNombreP(dtoPro.getNombreP()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese item ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoPro.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio!!"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = Sproyecto.getOne(id).get();
        proyecto.setNombreP(dtoPro.getNombreP());
        proyecto.setDescripcionP(dtoPro.getDescripcionP());
        Sproyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("El item fue modificado correctamente"), HttpStatus.OK);
        
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
        
        if(!Sproyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        Sproyecto.delete(id);
        return new ResponseEntity(new Mensaje("El item fue borrado correctamente"), HttpStatus.OK);
    }
}
