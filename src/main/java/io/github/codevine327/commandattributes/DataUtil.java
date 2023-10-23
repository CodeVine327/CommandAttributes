package io.github.codevine327.commandattributes;

import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DataUtil {
    public static void addStat(Player player, String statName, double statValue, boolean writeFile) {
        PlayerData playerData = PlayerData.get(player.getUniqueId());
        playerData.getStats().getMap().getInstance(statName).addModifier(new StatModifier("cmdatt", statName, statValue));

        if (writeFile) {
            writeData(player, statName, statValue);
        }
    }

    private static void writeData(Player player, String statName, double Value) {
        FileConfiguration config = CommandAttributes.config;
        config.set(player.getUniqueId() + ".PLAYER_NAME", player.getName());
        config.set(player.getUniqueId() + "." + statName, Value);
        CommandAttributes.plugin.saveConfig();
    }
}
