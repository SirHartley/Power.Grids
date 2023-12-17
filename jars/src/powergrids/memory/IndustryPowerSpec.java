package powergrids.memory;

public class IndustryPowerSpec {
    private String id;
    private float baseValue;
    private float exportValue;

    public IndustryPowerSpec(String id, float baseValue, float exportValue) {
        this.id = id;
        this.baseValue = baseValue;
        this.exportValue = exportValue;
    }

    public String getId() {
        return id;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public float getExportValue() {
        return exportValue;
    }
}
