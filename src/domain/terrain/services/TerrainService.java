package domain.terrain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Mobile;
import domain.unit.models.Unit;
import support.utils.AssetResponse;

public class TerrainService {
    
    public static Map<Measurable, AssetResponse<?>> toMonitor(
        Position position,
        List<Unit> units,
        Measurable ...measurables
    ) throws Exception {
        
        Map<Measurable, AssetResponse<?>> result = new HashMap<>();

        List<Unit> validUnits = TerrainService.getValidUnits(units, measurables);

        if(validUnits.isEmpty()) {
            throw new Exception("Não há uma unidade apta a realizar o monitoramento solicitado.");
        }

        Unit closest = Unit.getClosest(validUnits, position);

        if(closest == null) {
            throw new Exception("As seguintes unidades estão aptas, porém não posicionadas na área de cobertura.\n" 
                                + validUnits
            );
        }
            
        
        if(!closest.getPosition().equals(position)) {
            ((Mobile) closest).move(position);
        }

        for (Measurable measurable : measurables) {
            Class<?> assetClass = measurable.bindedAsset;

            AssetResponse<?> mr = closest.useAsset(assetClass, position);

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
