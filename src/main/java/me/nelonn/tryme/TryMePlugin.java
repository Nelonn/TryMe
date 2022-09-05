package me.nelonn.tryme;

import me.nelonn.tryme.commands.MeCommand;
import me.nelonn.tryme.commands.TryCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class TryMePlugin extends JavaPlugin {
    private static TryMePlugin instance;

    private Config config;

    @Override
    public void onEnable() {
        instance = this;

        config = new Config(this);

        new MeCommand(this).register(this);
        new TryCommand(this).register(this);
    }

    @Override
    public void onDisable() {
        config = null;
        instance = null;
    }

    @NotNull
    public Config getConfiguration() {
        return config;
    }

    public static TryMePlugin getInstance() {
        return instance;
    }
}
