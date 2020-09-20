package domain.unit.contracts;

import domain.terrain.models.aggregate.Position;

public interface Asset<T> {

    public T scan(Position position) throws Exception;
}
