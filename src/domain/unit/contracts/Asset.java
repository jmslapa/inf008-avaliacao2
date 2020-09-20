package domain.unit.contracts;

import domain.terrain.models.aggregate.Position;
import support.utils.MeasureResponse;

public interface Asset {

    public MeasureResponse<?> scan(Position position) throws Exception;
}
