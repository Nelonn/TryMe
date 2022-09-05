package me.nelonn.tryme.commands;

import me.nelonn.tryme.Config;
import me.nelonn.tryme.TryMePlugin;
import me.nelonn.tryme.utils.Command;
import me.nelonn.tryme.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TryCommand extends Command {
    private final Config config;

    public TryCommand(TryMePlugin plugin) {
        super("try");
        setUsageMessage("/<command> <message>");
        this.config = plugin.getConfiguration();
    }

    @Override
    protected void onCommand(@NotNull CommandSender sender, @NotNull String command, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return;
        if (args.length == 0) {
            sendUsageMessage(player, command);
            return;
        }
        String text = String.join(" ", args);
        if (text.length() > 64) {
            TextUtils.send(player, config.getInvalidMessageSize());
            return;
        }
        String message = TextUtils.color(config.getTryFormat())
                .replaceAll("<player>", player.getDisplayName())
                .replaceAll("<message>", text)
                .replaceAll("<result>", TextUtils.color(Math.random() < 0.5 ? config.getSuccess() : config.getFailed()));

        Location location = player.getLocation();
        for (Player receiver : Bukkit.getOnlinePlayers()) {
            if (config.getDistance() > 0) {
                if (!receiver.getWorld().equals(location.getWorld())) continue;
                double distance = receiver.getLocation().distance(location);
                if (distance > config.getDistance()) continue;
            }
            receiver.sendMessage(player.getUniqueId(), message);
        }
    }

    @Override
    protected List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return null;
    }
}
