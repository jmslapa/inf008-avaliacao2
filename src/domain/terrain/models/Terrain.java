package domain.terrain.models;

import java.util.Objects;

import domain.terrain.models.aggregate.Position;

public class Terrain {
    
    private int rows;
    private int columns;
    private Position[][] positions;

    public Terrain(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.make();
    }

    public Position[][] getPositions() {
        return this.positions;
    }

    public Position getPosition(int row, int column) {
        this.positionIsValid(row, column);
        return this.positions[row][column];
    }

    private void make() {        
        this.positions = new Position[this.rows][this.columns];
        for(int i = 0 ; i < this.positions.length ; i++) {
            for(int j = 0 ; j < this.positions[i].length ; j++) {
                this.positions[i][j] = new Position(i, j);
            }
        }
    }

    public void positionIsValid(int row, int column) {
        if(row < 0 || row >= this.rows) {
            throw new ArrayIndexOutOfBoundsException("A linha informada é inválida.");
        }
        if(column < 0 || column >= this.columns) {
            throw new ArrayIndexOutOfBoundsException("A coluna informada é inválida.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Terrain)) {
            return false;
        }
        Terrain terrain = (Terrain) o;
        return rows == terrain.rows && columns == terrain.columns && Objects.equals(positions, terrain.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, columns, positions);
    }

}
