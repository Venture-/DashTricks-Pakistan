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

    public double getTotalCapacity() {
        double sum = 0;
        for (Refrigerator r : quantities.keySet()) {
            int quantity = quantities.get(r);
            sum += r.getVolume() * quantity;
        }
        return sum;
    }

    public Capacity add(Refrigerator r) {
        Capacity ret = new Capacity(quantities);

        if (quantities.containsKey(r)) {
            ret.quantities.put(r, quantities.get(r) + 1);
        } else {
            ret.quantities.put(r, 1);
        }
        return ret;
    }
}
