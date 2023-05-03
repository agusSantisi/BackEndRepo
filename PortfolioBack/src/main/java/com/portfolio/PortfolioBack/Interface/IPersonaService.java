
package com.portfolio.PortfolioBack.Interface;

import com.portfolio.PortfolioBack.Entity.Persona;
import java.util.List;



public interface IPersonaService {
   
    //Método para traer la lista de personas de la base
    public List<Persona> getPersona();
    
    //Método para guardar una nueva persona en la base
    public void savePersona(Persona persona);
    
    //Método para borrar una persona de la base
    public void deletePersona(Long id);
    
    //Método para buscar una persona en la base
    public Persona findPersona(Long id);
}
