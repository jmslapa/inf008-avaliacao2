package application;

import java.util.Arrays;

import domain.terrain.enums.Measurable;
import domain.terrain.models.Terrain;
import domain.terrain.services.TerrainService;
import domain.unit.models.aggregate.CO2Measurer;
import domain.unit.models.aggregate.Camera;
import domain.unit.models.aggregate.MethaneMeasurer;
import domain.unit.models.aggregate.Thermometer;
import domain.unit.models.impl.EuclideanUnit;
import domain.unit.models.impl.ManhattanUnit;

public class Application {
    
    public static void run() throws Exception {
        Terrain terrain = new Terrain(5, 5);

        ManhattanUnit u1 = new ManhattanUnit(null, new CO2Measurer());
        u1.move(terrain.getPosition(3, 1));
        u1.setAssets(new MethaneMeasurer(), new Thermometer());

        EuclideanUnit u2 = new EuclideanUnit(null);
        u2.move(terrain.getPosition(1, 2));
        u2.setAssets(new Camera(), new Thermometer());
        
        System.out.println(
            TerrainService.toMonitor(terrain.getPosition(3, 4), Arrays.asList(u1, u2), Measurable.TEMPERATURE, Measurable.METHANE)
        );
    }

}
