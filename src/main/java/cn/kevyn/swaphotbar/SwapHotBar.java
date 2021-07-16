package cn.kevyn.swaphotbar;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SwapHotBar extends JavaPlugin {

    public final String ENABLE = ChatColor.BLUE + "[SwapHotBar] 插件已经成功启用！";
    public final String DISABLE = ChatColor.RED + "[SwapHotBar] 插件已经成功禁用！";
    public final String RELOAD = ChatColor.GREEN + "[SwapHotBar] 插件配置文件已重新功载入！";
    private SHBListener listener = new SHBListener(this);

    @Override
    public void onEnable() {
        super.onEnable();

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(listener, this);
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
        listener.loadConfig();
        getServer().getConsoleSender().sendMessage(RELOAD);
    }

}
