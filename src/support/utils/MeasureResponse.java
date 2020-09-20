package support.utils;

import java.util.Objects;

import domain.terrain.models.aggregate.Position;
import domain.unit.models.Unit;

public class MeasureResponse<T> {
    
    private T result;
    private String message;
    private Unit agent;
    private Position target;


    public MeasureResponse() {
    }

    public MeasureResponse(T result, String message) {
        this.result = result;
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public MeasureResponse<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public MeasureResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Unit getAgent() {
        return this.agent;
    }

    public MeasureResponse<T> setAgent(Unit agent) {
        this.agent = agent;
        return this;
    }

    public Position getTarget() {
        return this.target;
    }

    public MeasureResponse<T> setTarget(Position target) {
        this.target = target;
        return this;
    }

    public Boolean success() {
        
        if(this.result != null)
            return true;

        return false;
    }

    @Override    
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MeasureResponse)) {
            return false;
        }
        MeasureResponse<T> measureResponse = null;
        
        try {
            measureResponse = (MeasureResponse<T>) o; 
        } catch (Exception e) {
            return false;
        }
        return Objects.equals(result, measureResponse.result) && Objects.equals(message, measureResponse.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, message);
    }

    @Override
    public String toString() {
        return "{" +
            " result='" + getResult() + "'" +
            ", message='" + getMessage() + "'" +            
            ", agent_id='" + getAgent().getId() + "'" +            
            ", position_id='" + getTarget().getId() + "'" +
            "}";
    }

}
