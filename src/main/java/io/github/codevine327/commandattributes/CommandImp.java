package io.github.codevine327.commandattributes;

import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandImp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*
            命令格式：
            /cmdatt 玩家 属性名 属性
         */

        if (args.length == 1 && args[0].equalsIgnoreCase("debug")) {
            Player player = (Player) sender;
            PlayerData.get(player.getUniqueId()).getStats().getMap().getInstances().forEach(statInstance -> {
                System.out.println("---------------");
                System.out.println(statInstance.getStat() + "的所有修改器：");
                statInstance.getModifiers().forEach(statModifier -> {
                    System.out.println(statModifier.getKey() + statModifier.getValue());
                });
                System.out.println("----------------");
            });

            return true;
        }

        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "参数数量有误！");
            return true;
        }

        String playerName = args[0];
        String statName = args[1];
        int statValue = Integer.parseInt(args[2]);

        Player player = Bukkit.getPlayer(playerName);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + args[0] + "当前不在线！");
            return true;
        }

        DataUtil.addStat(player, statName, statValue, true);
        if (CommandAttributes.config.getBoolean("enable-command-message", true)) {
            sender.sendMessage("已为" + player.getName() + "添加自定义属性修改器，属性" + statName + "：" + statValue);
        }
        return true;
    }
}
