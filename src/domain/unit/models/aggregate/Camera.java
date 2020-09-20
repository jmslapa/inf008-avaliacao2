package domain.unit.models.aggregate;

import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;
import support.utils.MeasureResponse;

public class Camera implements Asset {

    @Override
    public MeasureResponse<?> scan(Position position) throws Exception {
        return new MeasureResponse<String>(position.takeAPicture(this), 
            "A imagem foi capturada com sucesso."
        );
    }
    
}
