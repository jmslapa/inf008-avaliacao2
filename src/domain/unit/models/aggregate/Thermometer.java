package domain.unit.models.aggregate;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;
import support.utils.AssetResponse;

public class Thermometer implements Asset {

    @Override
    public AssetResponse<?> scan(Position position) throws Exception {
        return new AssetResponse<Integer>(position.measure(this, Measurable.TEMPERATURE, Integer.class), 
            "A medição ocorreu com sucesso."
        );
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }    
}
