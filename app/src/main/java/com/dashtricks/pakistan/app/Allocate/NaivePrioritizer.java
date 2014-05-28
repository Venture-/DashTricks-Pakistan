package com.dashtricks.pakistan.app.Allocate;

import com.dashtricks.pakistan.app.General.Capacity;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.Refrigerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donohue on 5/7/14.
 */
public class NaivePrioritizer implements Prioritizer {
    private Map<Facility, Integer> requiredCapacity;
    private List<Facility> rankedFacilities;
    private Map<Facility, Capacity> currentState;

    private final Comparator<Facility> rankByMissingVolume;

    public NaivePrioritizer(Map<Facility, Capacity> initialState, final Map<Facility, Integer> requiredCapacity) {
        currentState = new HashMap<Facility, Capacity>(initialState);
        this.requiredCapacity = requiredCapacity;

        rankByMissingVolume = new Comparator<Facility>() {
            @Override
            public int compare(Facility facility1, Facility facility2) {
                double f2CapacityDeficit = requiredCapacity.get(facility2) - currentState.get(facility2).totalVolume();
                double f1CapacityDeficit = requiredCapacity.get(facility1) - currentState.get(facility1).totalVolume();
                return (int) Math.signum(f2CapacityDeficit - f1CapacityDeficit);
            }
        };


        rankedFacilities = new ArrayList<Facility>(initialState.keySet());
        Collections.sort(rankedFacilities, rankByMissingVolume);
    }

    @Override
    public Facility next() {
        return rankedFacilities.get(0);
    }

    @Override
    public void add(Refrigerator allocated) {
        Facility lastReturned = rankedFacilities.get(0);
        currentState.put(lastReturned, currentState.get(lastReturned).add(allocated));
        Collections.sort(rankedFacilities, rankByMissingVolume);
    }

    @Override
    public void leave() {
        rankedFacilities.remove(0);
    }

    @Override
    public boolean done() {
        return rankedFacilities.isEmpty();
    }

    @Override
    public double currentTotalCapacity() {
        return currentState.get(rankedFacilities.get(0)).totalVolume();
    }

    @Override
    public Map<Facility, Capacity> result() {
        return currentState;
    }


}
