package domain.unit.models.aggregate;

import domain.terrain.enums.Measurable;
import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;
import support.utils.AssetResponse;

public class Camera implements Asset {

    @Override
    public AssetResponse<?> scan(Position position) throws Exception {
        return new AssetResponse<String>(position.measure(this, Measurable.PICTURE, String.class), 
            "A imagem foi capturada com sucesso."
        );
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
    
}
