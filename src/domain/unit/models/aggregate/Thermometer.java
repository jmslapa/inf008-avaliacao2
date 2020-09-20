package domain.unit.models.aggregate;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;
import support.utils.MeasureResponse;

public class Thermometer implements Asset {

    @Override
    public MeasureResponse<?> scan(Position position) throws Exception {
        return new MeasureResponse<Integer>(position.measure(this, Measurable.TEMPERATURE, Integer.class), 
            "A medição ocorreu com sucesso."
        );
    }
    
}
