
package com.portfolio.PortfolioBack.Dto;

import jakarta.validation.constraints.NotBlank;


public class dtoHyS {
    @NotBlank
    private String tecnologia;
    @NotBlank
    private int porcentaje;

    public dtoHyS() {
    }

    public dtoHyS(String tecnologia, int porcentaje) {
        this.tecnologia = tecnologia;
        this.porcentaje = porcentaje;
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
