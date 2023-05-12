package plugin.facebookstreamplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.facebookstreamplugin.Utilities.FacebookClass;

public final class FacebookStreamPlugin extends JavaPlugin {

    FacebookClass facebookClass = new FacebookClass();
    String token = getConfig().getString("socketToken");

    public void checkToken() {
        if (token == null || token.isEmpty() || token.equalsIgnoreCase("yourSocketToken")) {
            Bukkit.getLogger().info("[FacebookStreamPlugin] Please use your proper token!");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            facebookClass.onFacebookEvent(this);
            Bukkit.getLogger().info("[FacebookStreamPlugin] Plugin successfully Enabled!");
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        checkToken();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[FacebookStreamPlugin] Plugin successfully Disabled!");
    }
}
