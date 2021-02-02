package com.example.model.shas_masechtot;

import java.util.List;


public class Shas {

    private List<Seder> seder;

    public Shas(List<Seder> seder) {
        this.seder = seder;
    }

    public List<Seder> getSeder() {
        return seder;
    }

    public void setSeder(List<Seder> seder) {
        this.seder = seder;
    }

}
