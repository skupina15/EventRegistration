package si.fri.rso.skupina15.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("rest-properties")
@ApplicationScoped
public class Properties {
    @ConfigValue(value = "maintenance-mode", watch = true)
    private boolean maintenece_mode;

    public boolean getMaintenece_mode() {
        return maintenece_mode;
    }

    public void setMaintenece_mode(boolean maintenece_mode) {
        this.maintenece_mode = maintenece_mode;
    }
}
