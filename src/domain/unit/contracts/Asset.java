package domain.unit.contracts;

import domain.terrain.models.aggregate.Position;
import support.utils.AssetResponse;

public interface Asset {

    public AssetResponse<?> scan(Position position) throws Exception;
}
 