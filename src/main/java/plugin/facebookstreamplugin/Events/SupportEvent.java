package plugin.facebookstreamplugin.Events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SupportEvent {

    public void onSupportEvent(String name, JavaPlugin plugin) {
        //Configuration settings
        boolean messageType = plugin.getConfig().getBoolean("messageType");
        boolean logMessages = plugin.getConfig().getBoolean("logMessages");
        boolean playSound = plugin.getConfig().getBoolean("playSound");

        //Get the streamer name to which the alert will be sent
        String streamerName = plugin.getConfig().getString("streamerName");
        if (streamerName == null) streamerName = "player";
        Player player =  Bukkit.getPlayer(streamerName);

        //Get the message that will be sent to the player
        String message = plugin.getConfig().getString("eventsMessages.support");
        String consoleMessage = "[Facebook] " + name + " became a supporter!";
        if (message == null || message.isEmpty()) return;
        message = message
                .replace("&","§")
                .replace("%name%",name);

        //Sending the message to the player
        if (player != null && player.isOnline()) {
            if (logMessages) { Bukkit.getLogger().info(consoleMessage); }
            if (playSound) { player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20f, 1f); }
            if (!messageType) {
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
            } else {
                player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
            }
        }

    }
}
