
package com.portfolio.PortfolioBack.Controller;

import com.portfolio.PortfolioBack.Dto.dtoPersona;
import com.portfolio.PortfolioBack.Entity.Persona;
import com.portfolio.PortfolioBack.Security.Controller.Mensaje;
import com.portfolio.PortfolioBack.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://frontendportfoliosantisi.web.app", "http://localhost:4200"})
@RequestMapping("/personas")
public class PersonaController {
    @Autowired 
    ImpPersonaService pService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = pService.list();
        return new ResponseEntity (list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!pService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = pService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    /*@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtoPer){
        if(StringUtils.isBlank(dtoPer.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(pService.existsByNombre(dtoPer.getNombre()))
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        
        Persona persona = new Persona(dtoPer.getNombre(), dtoPer.getApellido(), dtoPer.getDescripcion(), dtoPer.getImg());
        pService.save(persona);
        return new ResponseEntity(new Mensaje ("Educacion agregada"), HttpStatus.OK);
    }*/
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") int id, @RequestBody dtoPersona dtoPer){
        if(!pService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(pService.existsByNombre(dtoPer.getNombre()) && pService.getByNombre(dtoPer.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese item de educación ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoPer.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio!!"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoPer.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido es obligatorio!!"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPer.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria!!"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPer.getImg()))
            return new ResponseEntity(new Mensaje("La imagen es obligatoria!!"), HttpStatus.BAD_REQUEST);
        
        Persona persona = pService.getOne(id).get();
        persona.setNombre(dtoPer.getNombre());
        persona.setApellido(dtoPer.getApellido());
        persona.setDescripcion(dtoPer.getDescripcion());
        persona.setImg(dtoPer.getImg());
        pService.save(persona);
        return new ResponseEntity(new Mensaje("El item fue modificado correctamente"), HttpStatus.OK);
        
        
    }
    
    /*@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
        
        if(!pService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        pService.delete(id);
        return new ResponseEntity(new Mensaje("El item fue borrado correctamente"), HttpStatus.OK);
    }*/
    
}
    
 
   

