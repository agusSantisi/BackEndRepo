
package com.portfolio.PortfolioBack.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;
    private String nombreP;
    private String descripcionP;
    private String link;
    public Proyecto() {
    }

    public Proyecto(String nombreP, String descripcionP, String link) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.link = link;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
