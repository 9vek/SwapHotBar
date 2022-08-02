package cn.kevyn.swaphotbar;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SHBCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (command.getName().equals("shb-reload")) {
            SwapHotBar.INSTANCE.reloadPlugin();
            return true;
        }

        else if (command.getName().equals("shb-off")) {
            if ((commandSender instanceof Player)) {
                String playerName = commandSender.getName();
                SHBListener.INSTANCE.removeEnabledPlayer(playerName);
                commandSender.sendMessage("[Bot][快捷工具栏]" + ChatColor.RED + " 你的交换功能现在被禁用了. ");
                return true;
            }
        }

        else if (command.getName().equals("shb-on" )) {
            if ((commandSender instanceof Player)) {
                String playerName = commandSender.getName();
                SHBListener.INSTANCE.addEnabledPlayer(playerName);
                commandSender.sendMessage("[Bot][快捷工具栏]" + ChatColor.GREEN + " 你的交换功能现在开启了. ");
                return true;
            } 
        }

        else if (command.getName().equals("shb-change")) {
            if ((commandSender instanceof Player)) {
                String playerName = commandSender.getName();
                if (SHBListener.INSTANCE.isEnabledPlayer(playerName)) {
                    SHBListener.INSTANCE.removeEnabledPlayer(playerName);
                    commandSender.sendMessage("[Bot][快捷工具栏]" + ChatColor.RED + " 你的交换功能现在被禁用了. ");
                } else {
                    SHBListener.INSTANCE.addEnabledPlayer(playerName);
                    commandSender.sendMessage("[Bot][快捷工具栏]" + ChatColor.GREEN + " 你的交换功能现在开启了. ");
                }
                return true;
            }
        }
        return false;
    }

}
