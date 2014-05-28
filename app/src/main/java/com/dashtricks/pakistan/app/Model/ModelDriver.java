package com.dashtricks.pakistan.app.Model;

import com.dashtricks.pakistan.app.General.Facilities;
import com.dashtricks.pakistan.app.General.Facility;
import com.dashtricks.pakistan.app.General.ImmunizationPlan;
import com.dashtricks.pakistan.app.General.ImmunizationPlans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public class ModelDriver {
    public static Map<Facility, Integer> getRequirements(Facilities fs, ImmunizationPlans ips) {
        Map<Facility, Integer> ret = new HashMap<Facility, Integer>();

        for (Facility f : fs) {
            int requiredVolume = 0;
            for (ImmunizationPlan ip : ips) {
                requiredVolume += Calculator.computeVolume(f, ip);
            }
            ret.put(f, requiredVolume);
        }
        return ret;
    }
}
