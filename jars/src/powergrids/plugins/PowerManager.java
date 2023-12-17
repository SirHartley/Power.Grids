package powergrids.plugins;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MutableCommodityQuantity;
import powergrids.memory.Settings;

public class PowerManager {

    public static float getUnmodifiedTotalDraw(Industry industry){
        return getUnmodifiedBaseDraw(industry) + getUnmodifiedCommodityProductionDraw(industry);
    }

    public static float getModifiedTotalDraw(Industry industry){
        return getUnmodifiedTotalDraw(industry) * getPowerMult(industry.getMarket());
    }

    public static float getUnmodifiedBaseDraw(Industry industry){
        return Settings.getPowerSpec(industry).getBaseValue();
    }

    public static float getUnmodifiedCommodityProductionDraw(Industry industry){
        float numExports = 0f;
        for (MutableCommodityQuantity q : industry.getAllSupply()) numExports += q.getQuantity().getModifiedInt();
        return  Settings.getPowerSpec(industry).getExportValue() * numExports;
    }

    public static float getUnmodifiedTotalDraw(MarketAPI market) {
        float total = 0f;
        for (Industry ind : market.getIndustries()) total += getUnmodifiedTotalDraw(ind);

        return total;
    }

    public static float getPowerMult(MarketAPI market){
        float marketPop = (float) Math.pow(10f, market.getSize() + market.getPopulation().getWeightValue());
        return marketPop / Settings.POPULATION_PER_INCREMENT;
    }

    public static float getModifiedTotalDraw(MarketAPI market){
        return getUnmodifiedTotalDraw(market) * getPowerMult(market);
    }
}
