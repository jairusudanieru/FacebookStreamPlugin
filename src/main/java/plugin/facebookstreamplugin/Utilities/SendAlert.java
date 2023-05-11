package plugin.facebookstreamplugin.Utilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SendAlert {

    //Using the bossBar to send the event message
    public void sendBossBar(JavaPlugin plugin, String message) {
        //Check if broadcastMessage option is enabled
        boolean broadcastMessage = plugin.getConfig().getBoolean("broadcastMessage");
        if (broadcastMessage) {
            Server server = plugin.getServer();
            server.broadcastMessage(message);
            return;
        }

        //Get the streamer name to which the alert will be sent
        String streamerName = plugin.getConfig().getString("streamerName");
        if (streamerName == null) streamerName = "player";
        Player player =  Bukkit.getPlayerExact(streamerName);
        if (player == null || !player.isOnline()) return;

        //Check if the playSound option is enabled
        boolean playSound = plugin.getConfig().getBoolean("playSound");
        if (playSound) { player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20f, 1f); }

        //Send the bossBar to the player
        BossBar bossBar = Bukkit.createBossBar(message, BarColor.WHITE, BarStyle.SEGMENTED_10);
        bossBar.addPlayer(player);
        bossBar.setProgress(1.0);
        bossBar.setVisible(true);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            double progress = bossBar.getProgress();
            if (progress > 0.3333) {
                bossBar.setProgress(progress - 0.3333);
            } else {
                bossBar.setVisible(false);
                bossBar.removeAll();
            }
        }, 20L, 20L);
    }

    //Using the actionBar to send the event message
    public void sendActionBar(JavaPlugin plugin, String message) {
        //Check if broadcastMessage option is enabled
        boolean broadcastMessage = plugin.getConfig().getBoolean("broadcastMessage");
        if (broadcastMessage) {
            Server server = plugin.getServer();
            server.broadcastMessage(message);
            return;
        }

        //Get the streamer name to which the alert will be sent
        String streamerName = plugin.getConfig().getString("streamerName");
        if (streamerName == null) streamerName = "player";
        Player player =  Bukkit.getPlayerExact(streamerName);
        if (player == null || !player.isOnline()) return;

        //Check if the playSound option is enabled
        boolean playSound = plugin.getConfig().getBoolean("playSound");
        if (playSound) { player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20f, 1f); }

        //Send the actionBar to the player
        player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

}
