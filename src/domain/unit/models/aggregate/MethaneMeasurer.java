package domain.unit.models.aggregate;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;
import support.utils.MeasureResponse;

public class MethaneMeasurer implements Asset {

    @Override
    public MeasureResponse<?> scan(Position position) throws Exception {
        return new MeasureResponse<Double>(position.measure(this, Measurable.METHANE, Double.class), 
            "A medição ocorreu com sucesso."
        );
    }
    
}
