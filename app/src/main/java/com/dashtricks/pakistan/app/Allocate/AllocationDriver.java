package com.dashtricks.pakistan.app.Allocate;

import com.dashtricks.pakistan.app.General.Capacity;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.Refrigerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class AllocationDriver {
    public static Map<Facility, Capacity> allocate(Map<Facility, Capacity> initialState,
                                                   Map<Facility, Integer> requiredCapacity,
                                                   Map<Refrigerator, Integer> toAllocate) {

        Prioritizer prioritizer = new NaivePrioritizer(initialState, requiredCapacity);
        Map<Refrigerator, Integer> remainingToAllocate = new HashMap<Refrigerator, Integer>(toAllocate);

        while ( !(prioritizer.done() || toAllocate.isEmpty()) ) {
            Facility currentFacility = prioritizer.next();
            double shortage = requiredCapacity.get(currentFacility) - prioritizer.currentTotalCapacity();

            Refrigerator grantedRefrigerator = getAppropriateRefrigerator(currentFacility, shortage, remainingToAllocate.keySet());

            if (grantedRefrigerator != null) {
                remainingToAllocate.put(grantedRefrigerator, remainingToAllocate.get(grantedRefrigerator) - 1);
                if (remainingToAllocate.get(grantedRefrigerator) == 0) {
                    remainingToAllocate.remove(grantedRefrigerator);
                }

                prioritizer.add(grantedRefrigerator);
            } else {
                prioritizer.leave();
            }
        }
        return prioritizer.result();
    }

    // If a facilities shortage may be covered with a single refrigerator allocate the smallest
    // refrigerator that does so.
    // Otherwise allocate the largest refrigerator possible that may be used.
    private static Refrigerator getAppropriateRefrigerator(Facility currentFacility, double shortage, Set<Refrigerator> remainingToAllocate) {
        Refrigerator bestFit = null;
        for (Refrigerator r : remainingToAllocate) {
            if (!currentFacility.canUseRefrigerator(r)) {
                continue;
            }
            if (bestFit == null) {
                bestFit = r;
                continue;
            }

            if ((r.getVolume() > shortage && r.getVolume() < bestFit.getVolume())
                    || (r.getVolume() < shortage && r.getVolume() > bestFit.getVolume())) {
                bestFit = r;
            }
        }
        return bestFit;
    }


}
