package powergrids.plugins;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.campaign.listeners.PlayerColonizationListener;
import powergrids.conditions.PowerCondition;

public class PowerConditionApplicator implements PlayerColonizationListener, EconomyTickListener {

    public static void register(){
        ListenerManagerAPI manager = Global.getSector().getListenerManager();
        if (!manager.hasListenerOfClass(PowerConditionApplicator.class))
            manager.addListener(new PowerConditionApplicator(), true);
    }

    public static void applyToAllMarkets() {
        for (MarketAPI m : Global.getSector().getEconomy().getMarketsCopy()) PowerCondition.applyToMarket(m);
    }

    @Override
    public void reportPlayerColonizedPlanet(PlanetAPI planetAPI) {
        for (MarketAPI m : Global.getSector().getEconomy().getMarketsCopy()) PowerCondition.applyToMarket(m);
    }

    @Override
    public void reportPlayerAbandonedColony(MarketAPI marketAPI) {

    }

    @Override
    public void reportEconomyTick(int i) {
        applyToAllMarkets();
    }

    @Override
    public void reportEconomyMonthEnd() {
    }
}