package powergrids.memory;

import com.fs.starfarer.api.Global;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import powergrids.plugins.ModPlugin;

import java.io.IOException;

public class Importer {
    public static void loadPowerSpecs() {
        try {
            JSONArray config = Global.getSettings().getMergedSpreadsheetDataForMod("id", "data/config/power_draw.csv", "powergrids");
            for (int i = 0; i < config.length(); i++) {

                JSONObject row = config.getJSONObject(i);
                IndustryPowerSpec spec = new IndustryPowerSpec(row.getString("id"), row.getInt("base_draw"), row.getInt("draw_per_output"));
                Global.getSettings().putSpec(IndustryPowerSpec.class, spec.getId(), spec);
            }
        } catch (IOException | JSONException ex) {
            ModPlugin.log.error(ex);
        }
    }
}
