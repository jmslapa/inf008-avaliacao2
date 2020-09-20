package domain.unit.contracts;

public interface Mobile<T, R> {
    
    public void move(T target) throws Exception;

    public R getDistance(T target) throws Exception;
}
