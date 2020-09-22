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

        ManhattanUnit u1 = new ManhattanUnit(
            1L
            ,new MethaneMeasurer()
            ,new Thermometer()
            ,new Camera()
        );
        u1.move(terrain.getPosition(3, 3));

        EuclideanUnit u2 = new EuclideanUnit(
            2L
            ,new Camera()
            ,new Thermometer()
        );
        u2.move(terrain.getPosition(1, 2));

        ManhattanUnit u3 = new ManhattanUnit(
            3L
            ,new MethaneMeasurer()
            ,new CO2Measurer()
        );
        u3.move(terrain.getPosition(2, 4));

        EuclideanUnit u4 = new EuclideanUnit(
            4L
            ,new Camera()
            ,new CO2Measurer()
            ,new MethaneMeasurer()
            ,new Thermometer()
        );
        u4.move(terrain.getPosition(0, 0));
        
        System.out.println(
            TerrainService.toMonitor(
                terrain.getPosition(1, 1),
                Arrays.asList(u1, u2, u3, u4),
                Measurable.TEMPERATURE,
                Measurable.PICTURE
            )
        );

        System.out.println();
        for(int i = 0 ; i < terrain.getPositions().length ; i++) {
            for(int j = 0 ; j < terrain.getPositions()[i].length ; j++) {
                System.out.println(terrain.getPosition(i, j));
            }
        }
    }

}
