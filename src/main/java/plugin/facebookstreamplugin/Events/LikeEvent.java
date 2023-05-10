package plugin.facebookstreamplugin.Events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LikeEvent {

    public void onLikeEvent(String name, JavaPlugin plugin) {
        //Configuration settings
        boolean messageType = plugin.getConfig().getBoolean("messageType");
        boolean logMessages = plugin.getConfig().getBoolean("logMessages");
        boolean playSound = plugin.getConfig().getBoolean("playSound");

        //Get the streamer name to which the alert will be sent
        String streamerName = plugin.getConfig().getString("streamerName");
        if (streamerName == null) streamerName = "player";
        Player player =  Bukkit.getPlayer(streamerName);

        //Get the message that will be sent to the player
        String message = plugin.getConfig().getString("eventsMessages.like");
        String consoleMessage = "[Facebook] " + name + " liked the stream!";
        if (message == null || message.isEmpty()) return;
        message = message
                .replace("&","§")
                .replace("%name%",name);

        //Sending the message to the player
        if (player != null && player.isOnline()) {
            if (logMessages) { Bukkit.getLogger().info(consoleMessage); }
            if (playSound) { player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20f, 1f); }
            ChatMessageType chatMessageType = messageType ? ChatMessageType.CHAT : ChatMessageType.ACTION_BAR;
            player.sendMessage(chatMessageType, new TextComponent(message));
        }

    }
}
