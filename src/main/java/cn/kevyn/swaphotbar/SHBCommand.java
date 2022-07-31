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
                SHBListener.INSTANCE.addIgnoredPlayer(playerName);
                commandSender.sendMessage("[SwapHotBar]" + ChatColor.RED + " swap function is now disabled on you. ");
                return true;
            }
        }

        else if (command.getName().equals("shb-on" )) {
            if ((commandSender instanceof Player)) {
                String playerName = commandSender.getName();
                SHBListener.INSTANCE.removeIgnoredPlayer(playerName);
                commandSender.sendMessage("[SwapHotBar]" + ChatColor.GREEN + " swap function is now enabled on you. ");
                return true;
            } 
        }

        return false;
    }

}
