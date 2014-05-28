package com.dashtricks.pakistan.app.General;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Facility {
    private String name;
    private int facId;
    private String subdis;
    private double currentCapacity; // no direct set. Updated by adding refrigerators
    private double requiredCapacity; // Should ONLY be set with a value returned by the Calculator
    private double amountShortBy;
    private double percentDeficient;
    private int population;
    private Set<PowerSource> powerSources;
    private Set<Refrigerator> refrigerators;

    // All these things 
    public Facility(String name, int facId, Set<PowerSource> ps) {
        this.name = name;
        this.facId = facId;
        this.powerSources = ps;

    }

    public String getName() {
    return name;
    }
    
    public int getFacId() {
        return facId;
    }

    public boolean canUseSource(PowerSource p) {
        return powerSources.contains(p);
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    public int getPopulation() {
        return population;
    }

    // Done because Calculator imports Facility, and circular dependencies are ugly
    public void setRequiredCapacity(double rc){
        requiredCapacity = rc;
        amountShortBy = rc - currentCapacity;
        percentDeficient = (1 - currentCapacity/requiredCapacity) * 100;
    }

    public double getRequiredCapacity() {
        return requiredCapacity;
    }

    public String getSubdis() {
        return subdis;
    }

    public boolean canUseRefrigerator(Refrigerator r){
        //One day will be used to prevent refrigerators from being allocated to incorrect locations
        //for now, stub.
        return true;
    }
}
