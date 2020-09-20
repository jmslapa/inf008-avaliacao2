package domain.unit.models.impl;

import domain.unit.contracts.Asset;
import domain.unit.contracts.Mobile;
import domain.unit.models.Unit;

import java.math.BigDecimal;
import java.math.RoundingMode;

import domain.terrain.models.aggregate.Position;

public class EuclideanUnit extends Unit implements Mobile {

    public EuclideanUnit(Long id, Asset... assets) {
        super(id, assets);
    }

	@Override
    public void move(Position target) throws Exception {
        if(target.hasUnit())
            throw new Exception("A posição de destino está ocupada por outra unidade.");
        if(this.isPositioned()) {
            this.getPosition().checkOut(this);
            this.setPosition(null);
        }
        target.checkIn(this);
        this.setPosition(target);
    }

    @Override
    public Double getDistance(Position target) {
        
        Double dRow = (double) Math.abs(this.getPosition().getRow() - target.getRow());
        Double dColumn = (double) Math.abs(this.getPosition().getColumn() - target.getColumn());
        Double distance = Math.sqrt(Math.pow(dRow, 2) + Math.pow(dColumn, 2));
        return BigDecimal.valueOf(distance).setScale(2, RoundingMode.CEILING).doubleValue();
    }
    
}
