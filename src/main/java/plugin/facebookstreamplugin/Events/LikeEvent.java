package plugin.facebookstreamplugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.facebookstreamplugin.Utilities.SendAlert;

public class LikeEvent {

    SendAlert sendAlert = new SendAlert();

    public void onLikeEvent(String name, JavaPlugin plugin) {
        //Configuration settings
        boolean messageType = plugin.getConfig().getBoolean("messageType");
        boolean logMessages = plugin.getConfig().getBoolean("logMessages");

        //Get the message that will be sent to the player
        String message = plugin.getConfig().getString("eventsMessages.like");
        String consoleMessage = "[Facebook] " + name + " liked the stream!";
        if (message == null || message.isEmpty()) return;
        message = message.replace("&","§").replace("%name%",name);

        //Sending the message to the player
        if (logMessages) Bukkit.getLogger().info(consoleMessage);
        if (!messageType) {
            sendAlert.sendBossBar(plugin, message);
        } else {
            sendAlert.sendActionBar(plugin, message);
        }

    }
}
