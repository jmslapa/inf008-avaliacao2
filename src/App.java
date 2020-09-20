import domain.terrain.models.Terrain;
import domain.unit.models.aggregate.CO2Measurer;
import domain.unit.models.aggregate.Camera;
import domain.unit.models.aggregate.MethaneMeasurer;
import domain.unit.models.aggregate.Thermometer;
import domain.unit.models.impl.EuclideanUnit;
import domain.unit.models.impl.ManhattanUnit;

public class App {
    public static void main(String[] args) throws Exception {

        Terrain terrain = new Terrain(5, 5);

        ManhattanUnit u1 = new ManhattanUnit(null, new CO2Measurer());

        u1.move(terrain.getPosition(3, 2));
        u1.useAsset(CO2Measurer.class, Double.class);

        u1.setAssets(new MethaneMeasurer(), new Thermometer());
        u1.useAsset(MethaneMeasurer.class, Double.class);
        u1.useAsset(Thermometer.class, Integer.class);

        System.out.println("ManhattanDistance: " + u1.getDistance(terrain.getPosition(1, 4)) + " units");
        System.out.println();
        
        u1.move(terrain.getPosition(1, 4));

        EuclideanUnit u2 = new EuclideanUnit(null);
        u2.move(terrain.getPosition(3, 2));
        u2.setAssets(new Camera());
        System.out.println("Foi tirada uma foto. O arquivo foi salvo como: " + u2.useAsset(Camera.class, String.class));
        System.out.println("EuclideanDistance: " + u2.getDistance(terrain.getPosition(1, 4)) + " units");
    }
}
