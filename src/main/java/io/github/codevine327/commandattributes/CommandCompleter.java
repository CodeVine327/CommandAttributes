package io.github.codevine327.commandattributes;

import io.lumine.mythic.lib.api.stat.SharedStat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandCompleter implements TabCompleter {
    private final List<String> allStat = new ArrayList<>();

    public CommandCompleter() {
        this.initAllStat();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // 为命令执行者提供玩家名补全
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(name -> name.startsWith(args[0])).toList();
        }

        // 为命令执行者提供属性名补全
        if (args.length == 2) {
            return allStat.stream().filter(stat -> stat.startsWith(args[1])).toList();
        }

        return Collections.emptyList();
    }

    private void initAllStat() {
        Class<?> enumClass = SharedStat.class;
        Field[] fields = enumClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == String.class) {
                allStat.add(field.getName());
            }
        }
    }
}
