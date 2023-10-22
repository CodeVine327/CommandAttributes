package io.github.codevine327.commandattributes;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CommandAttributes extends JavaPlugin {
    public static FileConfiguration config;
    public static CommandAttributes plugin;

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        config = getConfig();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        plugin = this;
        Objects.requireNonNull(getCommand("cmdatt")).setExecutor(new CommandImp());
        Objects.requireNonNull(getCommand("cmdatt")).setTabCompleter(new CommandCompleter());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(),this);
    }

    @Override
    public void onDisable() {

    }
}
