package si.fri.rso.skupina15.beans.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("rest-properties")
@ApplicationScoped
public class RestProperties {
    @ConfigValue(value = "print", watch = true)
    private String print;

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }
}
