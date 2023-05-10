package plugin.facebookstreamplugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.facebookstreamplugin.Utilities.SendAlert;

public class StarsEvent {

    SendAlert sendAlert = new SendAlert();

    public void onStarsEvent(String name, String amount, JavaPlugin plugin) {
        //Configuration settings
        boolean messageType = plugin.getConfig().getBoolean("messageType");
        boolean logMessages = plugin.getConfig().getBoolean("logMessages");

        //Get the message that will be sent to the player
        String message = plugin.getConfig().getString("eventsMessages.stars");
        String consoleMessage = "[Facebook] " + name + " sent " + amount + " stars!";
        if (message == null || message.isEmpty()) return;
        message = message.replace("&","§").replace("%name%",name).replace("%amount%",amount);

        //Sending the message to the player
        if (logMessages) Bukkit.getLogger().info(consoleMessage);
        if (!messageType) {
            sendAlert.sendBossBar(plugin, message);
        } else {
            sendAlert.sendActionBar(plugin, message);
        }

    }
}
