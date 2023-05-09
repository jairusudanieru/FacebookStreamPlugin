package plugin.facebookstreamplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FacebookStreamPlugin extends JavaPlugin {

    FacebookClass facebookClass = new FacebookClass();
    String token = getConfig().getString("socketToken");

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        if (token == null || token.isEmpty() || token.equalsIgnoreCase("yourSocketToken")) return;
        facebookClass.onFacebookEvent(this);
        Bukkit.getLogger().info("[FacebookStreamPlugin] Plugin successfully Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[FacebookStreamPlugin] Plugin successfully Disabled!");
    }
}
