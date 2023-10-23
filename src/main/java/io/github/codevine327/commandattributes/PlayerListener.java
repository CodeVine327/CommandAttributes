package io.github.codevine327.commandattributes;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerListener implements Listener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = CommandAttributes.config;
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        ConfigurationSection allSta = config.getConfigurationSection(uuid.toString());
        if (allSta != null) {
            for (String key : allSta.getKeys(false)) {
                if (!key.equals("PLAYER_NAME")) {
                    DataUtil.addStat(player, key, config.getDouble(uuid + "." + key), false);
                }
            }
        }

    }
}
