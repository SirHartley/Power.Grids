package powergrids.conditions;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;

public class PowerCondition extends BaseMarketConditionPlugin {

    public static final String ID = "powergrids_powercondition";

    //keep track of output
    //keep track of requirement
    //if requirement > output, disable industries until output > requirement
    //disable for 7 days at a time and pick a random one each time unless there is an order specified (later)
    //show stats

    @Override
    public void apply(String id) {
        super.apply(id);
    }

    @Override
    public void unapply(String id) {
        super.unapply(id);

    }

    @Override
    public void advance(float amount) {
        super.advance(amount);

        //log current draw and do the whole calc each day
    }

    @Override
    public boolean showIcon() {
        return true;
    }

    public String getModId() {
        return condition.getId();
    }

    public static void applyToMarket(MarketAPI m) {
        if (m.isInEconomy() && !m.hasCondition(ID)) m.addCondition(ID);
    }
}