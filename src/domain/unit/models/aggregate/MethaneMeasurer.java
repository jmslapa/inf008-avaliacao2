package domain.unit.models.aggregate;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;

public class MethaneMeasurer implements Asset<Double> {

    @Override
    public Double scan(Position position) throws Exception {
        return position.measure(this, Measurable.METHANE, Double.class);
    }
    
}
