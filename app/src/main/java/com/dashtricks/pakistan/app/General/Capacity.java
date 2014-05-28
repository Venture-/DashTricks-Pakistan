package com.dashtricks.pakistan.app.General;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Donohue on 5/20/2014.
 */
public class Capacity {
    private final Map<Refrigerator, Integer> quantities;

    public Capacity() {
        quantities = new HashMap<Refrigerator, Integer>();
    }

    private Capacity(Map<Refrigerator, Integer> old) {
        quantities = new HashMap<Refrigerator, Integer>(old);
    }

    public double totalVolume() {
        double sum = 0.0;
        for (Map.Entry<Refrigerator, Integer> entry : quantities.entrySet()) {
            sum += entry.getValue() * entry.getKey().getVolume();
        }
        return sum;
    }

    public Capacity add(Refrigerator allocated) {
        Capacity copy = new Capacity(quantities);
        if (quantities.containsKey(allocated)) {
            copy.quantities.put(allocated, quantities.get(allocated) + 1);
        } else {
            copy.quantities.put(allocated, 1);
        }
        return copy;
    }
}
