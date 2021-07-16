package cn.kevyn.swaphotbar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SHBCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0)
            return false;

        else if (strings[0].equals("reload")) {
            ((SwapHotBar)commandSender.getServer().getPluginManager().getPlugin("SwapHotBar")).reloadPlugin();
            return true;
        }

        return false;
    }

}
