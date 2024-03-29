package cn.kevyn.swaphotbar;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SwapHotBar extends JavaPlugin {

    public static SwapHotBar INSTANCE;

    public final String ENABLE = "[SwapHotBar]" + ChatColor.BLUE + " plugin is now enabled！";
    public final String DISABLE = "[SwapHotBar]" + ChatColor.RED + " plugin is now disabled！";
    public final String RELOAD = "[SwapHotBar]" + ChatColor.GREEN + " config is now successfully reloaded！";

    private Permission permission;

    public SwapHotBar() {
        SwapHotBar.INSTANCE = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        saveDefaultConfig();

        dependenciesReady();

        CommandExecutor commandExecutor = new SHBCommand();
        getServer().getPluginManager().registerEvents(new SHBListener(), this);
        getCommand("shb-reload").setExecutor(commandExecutor);
        getCommand("shb-on").setExecutor(commandExecutor);
        getCommand("shb-off").setExecutor(commandExecutor);
        getServer().getConsoleSender().sendMessage(ENABLE);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        SHBListener.INSTANCE.saveIgnoredPlayers();
        getServer().getConsoleSender().sendMessage(DISABLE);
    }

    public void reloadPlugin() {
        reloadConfig();
        SHBListener.INSTANCE.loadConfig();
        getServer().getConsoleSender().sendMessage(RELOAD);
    }

    private boolean dependenciesReady() {

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Permission> rspp = getServer().getServicesManager().getRegistration(Permission.class);
        permission = rspp == null ? null : rspp.getProvider();

        return permission != null;

    }

    public Permission getPermission() {
        return permission;
    }

}
