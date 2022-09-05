package me.nelonn.tryme;

import me.nelonn.tryme.utils.ConfigWrapper;
import org.bukkit.plugin.Plugin;

public class Config {
    private final ConfigWrapper config;

    private double distance;
    private String invalidMessageSize;
    private String meFormat;
    private String success;
    private String failed;
    private String tryFormat;

    public Config(Plugin plugin) {
        config = new ConfigWrapper(plugin, "config.yml", true);
        load();
    }

    public void load() {
        distance = (int) config.get("distance");
        invalidMessageSize = (String) config.get("invalid_message_size");
        meFormat = (String) config.get("me_format");
        success = (String) config.get("success");
        failed = (String) config.get("failed");
        tryFormat = (String) config.get("try_format");
    }

    public double getDistance() {
        return distance;
    }

    public String getInvalidMessageSize() {
        return invalidMessageSize;
    }

    public String getMeFormat() {
        return meFormat;
    }

    public String getSuccess() {
        return success;
    }

    public String getFailed() {
        return failed;
    }

    public String getTryFormat() {
        return tryFormat;
    }
}
