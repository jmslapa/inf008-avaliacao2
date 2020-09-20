package domain.unit.models.aggregate;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;

public class Thermometer implements Asset<Integer> {

    @Override
    public Integer scan(Position position) throws Exception {
        return position.measure(this, Measurable.TEMPERATURE, Integer.class);
    }
    
}
