
package com.portfolio.PortfolioBack.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class HyS {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String tecnologia;
    private int porcentaje;


    public HyS(String tecnologia, int porcentaje) {
        this.tecnologia = tecnologia;
        this.porcentaje = porcentaje;
    }
    
    public HyS() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
