package domain.terrain.enums;

import domain.unit.models.aggregate.CO2Measurer;
import domain.unit.models.aggregate.Camera;
import domain.unit.models.aggregate.MethaneMeasurer;
import domain.unit.models.aggregate.Thermometer;

public enum Measurable {
    
    CO2(CO2Measurer.class),
    METHANE(MethaneMeasurer.class),
    TEMPERATURE(Thermometer.class),
    PICTURE(Camera.class);

    public final Class<?> bindedAsset;

    private Measurable(Class<?> bindedAsset) {
        this.bindedAsset = bindedAsset;
    }
}
