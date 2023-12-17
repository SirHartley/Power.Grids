package powergrids.listener;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.listeners.BaseIndustryOptionProvider;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import powergrids.memory.IndustryPowerSpec;
import powergrids.memory.Settings;
import powergrids.plugins.PowerManager;

public class IndustryPowerTooltipAdder extends BaseIndustryOptionProvider {

    public static void register() {
        ListenerManagerAPI manager = Global.getSector().getListenerManager();
        if (!manager.hasListenerOfClass(IndustryPowerTooltipAdder.class))
            manager.addListener(new IndustryPowerTooltipAdder(), true);
    }

    @Override
    public boolean isUnsuitable(Industry ind, boolean allowUnderConstruction) {
        return super.isUnsuitable(ind, allowUnderConstruction) || ind.getSpec().hasTag(Settings.POWER_PLANT_TAG);
    }

    @Override
    public void addToIndustryTooltip(Industry ind, Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, float width, boolean expanded) {
        if (isUnsuitable(ind, false)) return;
        float opad = 10f;

        tooltip.addSectionHeading("Power Management", Alignment.MID, opad);
        IndustryPowerSpec spec = Settings.getPowerSpec(ind);

        float totalDraw = PowerManager.getModifiedTotalDraw(ind);

        if (totalDraw > 0){
            //> 0 means it consumes power
            tooltip.beginGridFlipped(250f, 1, 100f, opad);
            tooltip.addToGrid(1,1,  "Base Draw", Math.round(PowerManager.getUnmodifiedBaseDraw(ind)) + " MWh");
            tooltip.addToGrid(1,2,  "Production Draw", Math.round(PowerManager.getUnmodifiedCommodityProductionDraw(ind)) + " MWh");
            tooltip.addToGrid(1,3,  "Colony Size", Math.round(PowerManager.getPowerMult(ind.getMarket())) + "x");
            tooltip.addToGrid(1,4,  "Total", Math.round(totalDraw) + " MWh", Misc.getHighlightColor());

            tooltip.addGrid(opad);
        }

        if (totalDraw < 0) {
            //todo <0 means it produces power which makes it a power plant - custom handling for power plants via API (displayPowerplantTooltip or smth) goes here

        }

        if (totalDraw == 0) {
            //todo its disabled and has 0 draw
        }
    }
}