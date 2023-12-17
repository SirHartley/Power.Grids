package powergrids.memory;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.Industry;

public class Settings {
    public static final float POPULATION_PER_INCREMENT = 1000;
    public static final String POWER_PLANT_TAG = "powerplant";

    public static IndustryPowerSpec getPowerSpec(Industry industry){
        IndustryPowerSpec spec = (IndustryPowerSpec) Global.getSettings().getSpec(IndustryPowerSpec.class, industry.getId(), true);
        if (spec == null) spec = createAndSavePowerSpec(industry);

        return spec;
    }

    private static IndustryPowerSpec createAndSavePowerSpec(Industry industry){
        //actual numbers Dependent on total exported goods num, industry type (rural, normal, industrial, urban), if structure or industry, and base upkeep cost

        IndustryPowerSpec spec = new IndustryPowerSpec(industry.getId(), 30f, 30f); //todo adjust with actual numbers
        Global.getSettings().putSpec(IndustryPowerSpec.class, spec.getId(), spec);
        return spec;
    }
}
