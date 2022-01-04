package cn.kevyn.swaphotbar;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class SHBListener implements Listener {

    public static SHBListener INSTANCE;

    private SwapHotBar shb;
    private List<Player> list;
    private int swapInterval;

    public SHBListener() {
        SHBListener.INSTANCE = this;
        this.shb = SwapHotBar.INSTANCE;
        list = new ArrayList<>();
        loadConfig();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerSwap(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        Permission permission = shb.getPermission();
        if (permission != null) {
            if (permission.has(player, "shb.ignore"))
                return;
        }

        if (list.contains(player))
            return;

        if (!player.isSneaking())
            return;

        PlayerInventory inventory = player.getInventory();
        int previousSlot = event.getPreviousSlot();
        int newSlot = event.getNewSlot();
        int t = newSlot - previousSlot;

        synchronized (inventory) {
            if (t == 1 || t == -8) {
                List<ItemStack> bar3 = copyBar(inventory, 3);
                writeBar(inventory, 3, copyBar(inventory, 2));
                writeBar(inventory, 2, copyBar(inventory, 1));
                writeBar(inventory, 1, copyBar(inventory, 0));
                writeBar(inventory, 0, bar3);
            } else if (t == -1 || t == 8) {
                List<ItemStack> bar0 = copyBar(inventory, 0);
                writeBar(inventory, 0, copyBar(inventory, 1));
                writeBar(inventory, 1, copyBar(inventory, 2));
                writeBar(inventory, 2, copyBar(inventory, 3));
                writeBar(inventory, 3, bar0);
            }

            inventory.setHeldItemSlot(event.getPreviousSlot());

        }

        list.add(player);
        BukkitScheduler scheduler = shb.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(shb, new Runnable() {
            @Override
            public void run() {
                list.remove(player);
            }
        }, swapInterval);

    }

    private List<ItemStack> copyBar(PlayerInventory inventory, int n) {
        List<ItemStack> bar = new ArrayList<>();
        if (n < 0 || n > 3)
            return null;

        for (int i = n * 9; i < 9 + n * 9; i++) {
            bar.add(inventory.getItem(i));
        }

        return bar;
    }

    private void writeBar(PlayerInventory inventory, int n, List<ItemStack> bar) {
        if (n < 0 || n > 3)
            return;

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i + n * 9, bar.get(i));
        }
    }

    public void loadConfig() {
        swapInterval = shb.getConfig().getInt("swap-interval");
        if (swapInterval < 1)
            swapInterval = 1;
    }

}
