# SwapHotBar
A minecraft spigot plugin that change the mechanism of the backpack and the hot bar, swap rows' of items in your backpack to your hot bar by simply using **shift + mouse wheel**

SpigotMC release: [SwapHotBar | SpigotMC - High Performance Minecraft](https://www.spigotmc.org/resources/swaphotbar.94333/)

MCBBS release: [\[机制\]蛋挞君的快捷物品栏切换——滚动快捷栏，更高效率，更多乐趣！[1.12-1.17]](https://www.mcbbs.net/thread-1215585-1-1.html)

## INTRODUCTION

There are some mods (for example Quark) that allow players to switch between multiple hot bars.

SwapHotBar is a plugin that implement this feature in a simple way, by changing the relationship between hot bar and backpack: each row of player's backpack can be seen as a hot bar, when the player is in the game, using **shift + mouse wheel** can swap the items in the next row (or previous row) of backpack to the hot bar, while the current hot bar's items will swap back to the backpack, that form a cycle. 

In sum, this plugin has the following 2 features: 

- Players are able to swap between backpack rows without open the backpack.
- **[1.5 new]** Players can **turn on or turn off** this function by themselves

This mechanism is very convenient in the game. The application scenarios include but are not limited to: 

- swapping food, weapons, and tools
- swapping tons of building blocks
- quickly searching for an item
- etc...

Just see the following sample inventory and the GIF to understand the usage:

![IMG](/screenshots/screenshot0.png)



![IMG](./screenshots/exampleusage.gif)

## INSTALLATION

- Please **fully test before use**. If you have any problem, please give me feedback as soon as possible.
- If the inventory's slot numbers has been modified, this plugin may have problems.

## PERMISSIONS & COMMANDS

There are 2 permissions and  3 commands:

```shell
# players by default have this permission:
shb.switch
# and they can use the following two commands to turn on / off this function:
shb-on
shb-off

# op by default have this permission:
shb.reload
# to reload the config file, type:
shb-reload
```



