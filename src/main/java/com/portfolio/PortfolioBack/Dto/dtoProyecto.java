
package com.portfolio.PortfolioBack.Dto;

import jakarta.validation.constraints.NotBlank;


public class dtoProyecto {
    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String link;

    public dtoProyecto() {
    }

    public dtoProyecto(String nombreP, String descripcionP, String link) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.link = link;
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
