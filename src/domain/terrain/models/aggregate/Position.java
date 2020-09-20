package domain.terrain.models.aggregate;

import java.util.Objects;
import java.util.UUID;

import domain.terrain.enums.Measurable;
import domain.unit.contracts.Asset;
import domain.unit.models.Unit;

public class Position {
    
    private Long id;
    private int row;
    private int column;
    private Unit currentUnit;

    /**
     * Construtor da classe
     * @param row numero de linhas da matriz
     * @param column numero de colunas da matriz
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.currentUnit = null;
    }

    public Long getId() {
        return this.id;
    }

    public Position setId(Long id) {
        this.id = id;
        return this;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Position setRow(int row) {
        this.row = row;
        return this;
    }

    public Position setColumn(int column) {
        this.column = column;
        return this;
    }

    /**
     * Verifica se já existe uma unidade de monitoramento posicionada
     * @return boolean
     */
    public Boolean hasUnit() {
        if(this.currentUnit != null) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param unit unidade a ser posicionada
     * @throws Exception
     */
    public void checkIn(Unit unit) throws Exception {
        if(this.hasUnit()) {
            throw new Exception("Já existe uma unidade atualmente posicionada.");
        }
        this.currentUnit = unit;
    }

    public void checkOut(Unit unit) throws Exception {
        this.validadeCurrentUnit(unit);
        this.currentUnit = null;
    }

    public Unit getCurrentUnit() {
        return this.currentUnit;
    }

    public <T> T measure(Asset asset, Measurable measurable, Class<T> returnType) throws Exception {
        
        this.validateAsset(asset);
        
        switch(measurable) {
            case CO2:
                return returnType.cast(0.0);
            case METHANE:
                return returnType.cast(0.0);
            case TEMPERATURE:
                return returnType.cast(0);
            default: 
                return null;
        }
    }

    public String takeAPicture(Asset asset) {
        return UUID.randomUUID().toString() + ".jpeg";
    }

    private void validadeCurrentUnit(Unit unit) throws Exception {
        if(!this.getCurrentUnit().equals(unit))
            throw new Exception("A unidade que solicitou para deixar a posiçao, não está atualmente alocada.");
    }

    private void validateAsset(Asset asset) throws Exception {
        if(!this.hasUnit())
            throw new Exception("Não há nenhuma unidade de monitoramento posicionada.");
        if(!this.getCurrentUnit().hasAsset(asset))
            throw new Exception("O asset que requisitou a medição, não pertence à unidade atualmente alocada.");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "{" +
            " row='" + getRow() + "'" +
            ", column='" + getColumn() + "'" +
            "}";
    }
    
}
