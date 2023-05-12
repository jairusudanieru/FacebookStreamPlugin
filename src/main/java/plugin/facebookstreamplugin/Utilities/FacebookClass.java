package plugin.facebookstreamplugin.Utilities;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import plugin.facebookstreamplugin.Events.*;

public class FacebookClass {

    FollowEvent follow = new FollowEvent();
    LikeEvent like = new LikeEvent();
    ShareEvent share = new ShareEvent();
    StarsEvent stars = new StarsEvent();
    SupportEvent support = new SupportEvent();

    public void onFacebookEvent(JavaPlugin plugin) {
        try {
            final String socketToken = plugin.getConfig().getString("socketToken");
            Socket socket;
            try {
                socket = IO.socket("https://sockets.streamlabs.com?token=" + socketToken);
            } catch (Exception error) {
                Bukkit.getLogger().severe("Failed to create socket: " + error.getMessage());
                return;
            }

            socket.on("event", args -> {
                JSONObject streamData = (JSONObject) args[0];
                if (!streamData.has("for") && streamData.getString("type").equals("donation")) {
                    Bukkit.getLogger().info(streamData.getString("message"));
                }
                if (streamData.has("for") && streamData.getString("for").equals("facebook_account")) {
                    JSONArray messageArray = streamData.getJSONArray("message");
                    String name = "user", amount = "0";
                    for (int i = 0; i < messageArray.length(); i++) {
                        JSONObject messageObj = messageArray.getJSONObject(i);
                        try { name = messageObj.getString("name"); } catch (Exception ignored) {}
                        try { amount = messageObj.getString("amount"); } catch (Exception ignored) {}
                    }
                    switch (streamData.getString("type")) {
                        case "follow":
                            //System.out.println(streamData);
                            follow.onFollowEvent(name, plugin);
                            break;
                        case "like":
                            //System.out.println(streamData);
                            like.onLikeEvent(name, plugin);
                            break;
                        case "share":
                            //System.out.println(streamData);
                            share.onShareEvent(name, plugin);
                            break;
                        case "stars":
                            //System.out.println(streamData);
                            stars.onStarsEvent(name, amount, plugin);
                            break;
                        case "support":
                            //System.out.println(streamData);
                            support.onSupportEvent(name, plugin);
                            break;
                    }
                }
            });
            socket.connect();
            Bukkit.getLogger().info("Socket Connected!");
        } catch (Exception error) {
            Bukkit.getLogger().severe("Something went wrong on FacebookEvent!");
        }
    }

}
