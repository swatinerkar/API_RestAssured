package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * application.conf file reader. Loads the application.conf file and maintains a single reference object of ConfigReader class
 */
@Slf4j
public class ConfigReader {
    private static final ConfigReader CONFIG = new ConfigReader();
    private Config config;

    private ConfigReader() {
        MDC.put("testContext", "Set Config");
        config = setConfig();
        MDC.clear();
    }

    public static ConfigReader getInstance() {
        return CONFIG;
    }

    public Config getConfig() {
        return config;
    }

    public Config setConfig() {
        // Standard config load behavior (loads common config from application.conf file)
        // https://github.com/lightbend/config#standard-behavior
        this.config = ConfigFactory.load();
        return this.config;
    }

}
