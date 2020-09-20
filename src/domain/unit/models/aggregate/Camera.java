package domain.unit.models.aggregate;

import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;

public class Camera implements Asset<String>{

    @Override
    public String scan(Position position) throws Exception{
        return position.takeAPicture(this);
    }
    
}
