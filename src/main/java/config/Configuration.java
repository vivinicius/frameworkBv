

package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:conf/general.properties"})
public interface Configuration extends Config {

    @Key("target")
    String target();

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("url")
    String url();

    @Key("timeout")
    String timeout();

    @Key("print.failure")
    Boolean printFailure();

    @Key("print.scenario.end")
    Boolean printScenarioEnd();

    @Key("print.before.step")
    Boolean printBeforeStep();

    @Key("print.after.step")
    Boolean printAfterStep();

}
