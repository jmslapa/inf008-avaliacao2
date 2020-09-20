package domain.unit.contracts;

import domain.terrain.models.aggregate.Position;

public interface Mobile {
    
    public void move(Position target) throws Exception;

    public Double getDistance(Position target) throws Exception;
}
