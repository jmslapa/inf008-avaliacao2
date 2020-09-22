package support.seeds;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import domain.unit.contracts.Asset;
import domain.unit.models.Unit;
import domain.unit.models.impl.EuclideanUnit;
import domain.unit.models.impl.ManhattanUnit;

public class UnitSeeder {

    public static List<Unit> seed(Integer quantity) {
        List<Unit> units = new ArrayList<>();
        List<Class<? extends Unit>> unitsClasses = Arrays.asList(ManhattanUnit.class, EuclideanUnit.class);

        for(int i = 0 ; i < quantity ; i++) {
            units.add(UnitSeeder.getRandomUnit(unitsClasses, (long) i));
        }
        
        return units;
    }

    private static Unit getRandomUnit(List<Class<? extends Unit>> list, Long id) {
        int rnd = new Random().nextInt(list.size());
        Unit result = null;
        try {
            result = list.get(rnd)
                        .getDeclaredConstructor(Long.class, Asset[].class)
                        .newInstance(id, Array.newInstance(Asset.class, 0));
        } catch ( InstantiationException 
                | IllegalAccessException 
                | IllegalArgumentException 
                | InvocationTargetException
                | NoSuchMethodException 
                | SecurityException e) {
                    
            e.printStackTrace();
        }
        return result;
    }

}
