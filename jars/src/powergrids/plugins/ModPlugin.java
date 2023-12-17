package powergrids.plugins;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import org.apache.log4j.Logger;
import powergrids.listener.IndustryPowerTooltipAdder;
import powergrids.memory.Importer;

public class ModPlugin extends BaseModPlugin {
    public static final Logger log = Global.getLogger(ModPlugin.class);

    //notes
    //going to have to scale power generation somehow or base consumption is going to overtake output consumption immediately, or dial down the population consumption by a lot to avoid having to scale things (it's bad!)
    //use negative power draws for the power plants?

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);

        PowerConditionApplicator.register();
        IndustryPowerTooltipAdder.register();
    }

    @Override
    public void onApplicationLoad() throws Exception {
        super.onApplicationLoad();
        Importer.loadPowerSpecs();
    }
}
