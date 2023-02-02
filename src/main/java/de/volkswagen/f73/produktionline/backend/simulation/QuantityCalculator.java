package de.volkswagen.f73.produktionline.backend.simulation;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */

public class QuantityCalculator {

    public long calculateQuantity(List<Long> productionTimeFromParts, float timeFactor, float carModifier, long passedTime, long maxRunTimeByRobot) {
        long wholeProductionTime = 0;
        long longesStep = 0;
        long carModifierAsLong = (long) (carModifier * 10);
        long simTime = passedTime * (long) (timeFactor * 10);
        for (long i : productionTimeFromParts) {
            wholeProductionTime += i;
            if (i >= longesStep) {
                longesStep = i;
            }
        }
        if (simTime > maxRunTimeByRobot) {
            simTime = maxRunTimeByRobot;
        }
        long firstStep = (wholeProductionTime * carModifierAsLong);
        long everyStep = (longesStep * carModifierAsLong);
        return ((simTime - firstStep) / everyStep);
    }


}
