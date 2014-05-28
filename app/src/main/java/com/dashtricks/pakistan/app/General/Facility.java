package com.dashtricks.pakistan.app.General;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class Facility {
    private String name;
    private String facId;
    private double latitude;
    private double longitude;
    private Set<PowerSource> powerSources;
    
    private int population;
    private int deliveriesPerYear;

    // All these things 
    public Facility(String name, String facId,
		            double latitude, double longitude,
                    Set<PowerSource> ps, int deliveriesPerYear) {
        this.name = name;
        this.facId = facId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.powerSources = ps;
        this.deliveriesPerYear = deliveriesPerYear;
    }

    public String getName() {
	return name;
    }
    
    public String getFacId() {
	return facId;
    }
    
    public double getLatitude() {
	return latitude;
    }
    
    public double getLongitude() {
	return longitude;
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

    public int getDeliveriesPerYear() {
        return deliveriesPerYear;
    }

    public boolean canUseRefrigerator(Refrigerator r) {
        return true;
    }
}
