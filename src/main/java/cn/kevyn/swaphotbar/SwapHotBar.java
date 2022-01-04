package cn.kevyn.swaphotbar;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SwapHotBar extends JavaPlugin {

    public static SwapHotBar INSTANCE;

    public final String ENABLE = ChatColor.BLUE + "[SwapHotBar] 插件已经成功启用！";
    public final String DISABLE = ChatColor.RED + "[SwapHotBar] 插件已经成功禁用！";
    public final String RELOAD = ChatColor.GREEN + "[SwapHotBar] 插件配置文件已重新功载入！";

    private Permission permission;

    public SwapHotBar() {
        SwapHotBar.INSTANCE = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        saveDefaultConfig();

        dependenciesReady();

        getServer().getPluginManager().registerEvents(new SHBListener(), this);
        getCommand("shb").setExecutor(new SHBCommand());

        getServer().getConsoleSender().sendMessage(ENABLE);
    }

    @Override
    public void onDisable() {
        super.onDisable();
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
