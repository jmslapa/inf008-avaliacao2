package domain.unit.models.aggregate;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;
import support.utils.AssetResponse;

public class MethaneMeasurer implements Asset {

    @Override
    public AssetResponse<?> scan(Position position) throws Exception {
        return new AssetResponse<Double>(position.measure(this, Measurable.METHANE, Double.class), 
            "A medição ocorreu com sucesso."
        );
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }    
}
