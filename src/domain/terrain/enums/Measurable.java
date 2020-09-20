package domain.terrain.enums;

public enum Measurable {
    
    CO2("CO2"),
    METHANE("Metano"),
    TEMPERATURE("Temperatura");

    public final String label;

    private Measurable(String label) {
        this.label = label;
    }
}
