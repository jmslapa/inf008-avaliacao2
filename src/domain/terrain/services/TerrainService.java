package domain.terrain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.models.Unit;
import support.utils.MeasureResponse;

public class TerrainService {
    
    public static Map<Measurable, MeasureResponse<?>> toMonitor(
        Position position,
        List<Unit> units,
        Measurable ...measurables
    ) throws Exception {
        
        Map<Measurable, MeasureResponse<?>> result = new HashMap<>();

        List<Unit> validUnits = TerrainService.getValidUnits(units, measurables);      
        
        Unit closest = Unit.getClosest(validUnits, position);

        for (Measurable measurable : measurables) {
            Class<?> assetClass = measurable.bindedAsset;
            MeasureResponse<?> mr = closest.useAsset(assetClass);

            result.put(measurable, mr);
        }

        return result;
    }

    private static List<Unit> getValidUnits(List<Unit> units, Measurable ...measurables) {
        List<Class<?>> objectClasses = new ArrayList<>();
        for (Measurable measurable : measurables) {
            objectClasses.add(measurable.bindedAsset);
        }

        List<Unit> validUnits = units.stream()
                                .filter(u -> u.hasAssets(objectClasses))
                                .collect(Collectors.toList());

        return validUnits;
    }

}
