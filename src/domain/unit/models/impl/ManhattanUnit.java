package domain.unit.models.impl;

import domain.unit.contracts.Asset;
import domain.unit.contracts.Mobile;
import domain.unit.models.Unit;
import domain.terrain.models.aggregate.Position;

public class ManhattanUnit extends Unit implements Mobile<Position, Integer> {

    public ManhattanUnit(Long id, Asset<?>... assets) {
        super(id, assets);
    }

	@Override
    public void move(Position target) throws Exception {
        if(!target.isAvailable())
            throw new Exception("A posição de destino está ocupada por outra unidade.");
        if(this.isPositioned()) {
            this.getPosition().leave(this);
            this.setPosition(null);
        }
        target.toMonitor(this);
        this.setPosition(target);
    }

    @Override
    public Integer getDistance(Position target) throws Exception {
        if(!this.isPositioned()) 
            throw new Exception("A unidade não está posicionada");
        Integer dRow = Math.abs(this.getPosition().getRow() - target.getRow());
        Integer dColumn = Math.abs(this.getPosition().getColumn() - target.getColumn());
        return dRow + dColumn;
    }
    
}
