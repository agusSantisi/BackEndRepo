
package com.portfolio.PortfolioBack.Controller;

import com.portfolio.PortfolioBack.Dto.dtoHyS;
import com.portfolio.PortfolioBack.Entity.HyS;
import com.portfolio.PortfolioBack.Security.Controller.Mensaje;
import com.portfolio.PortfolioBack.Service.SHyS;
import java.util.HashSet;
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
@CrossOrigin(origins = {"https://frontendportfoliosantisi.web.app", "http://localhost:4200"})
@RequestMapping("/skills")
public class CHyS {
    
    @Autowired
    SHyS shys;
    
    @GetMapping("/list")
    public ResponseEntity<List<HyS>> list(){
        List<HyS> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById(@PathVariable("id") int id){
        if(!shys.existsById(id)){
            return new ResponseEntity(new Mensaje("El item no existe"), HttpStatus.NOT_FOUND);
        }
        HyS hys = shys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyS dtohys) {
        if(StringUtils.isBlank(dtohys.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(shys.existsByNombre(dtohys.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        HyS hys = new HyS (dtohys.getNombre(), dtohys.getPorcentaje());
        shys.save(hys);
        return new ResponseEntity(new Mensaje("El item fue agregado"), HttpStatus.BAD_REQUEST);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHyS dtohys){
        if (!shys.existsById(id)){
            return new ResponseEntity(new Mensaje("El item no existe"), HttpStatus.BAD_REQUEST);
        }
        if(shys.existsByNombre(dtohys.getNombre()) && shys.getByNombre(dtohys.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese item ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtohys.getNombre())){
            return new ResponseEntity(new Mensaje("¡El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
        }
        
        
        HyS hys = shys.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje(dtohys.getPorcentaje());
        
        shys.save(hys);
        return new ResponseEntity(new Mensaje("El item fue actualizado correctamente"), HttpStatus.OK);
    } 
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!shys.existsById(id)){
            return new ResponseEntity(new Mensaje("Este item no existe"), HttpStatus.NOT_FOUND);
        }
        
        shys.delete(id);
        return new ResponseEntity(new Mensaje("El item fue elminiado correctamente"), HttpStatus.OK);
    }
}
