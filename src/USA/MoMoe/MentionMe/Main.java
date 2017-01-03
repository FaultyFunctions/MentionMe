package USA.MoMoe.MentionMe;

import USA.MoMoe.MentionMe.Hooks.DeluxeChatHook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {
    // Enable the config object
    private FileConfiguration config = getConfig();
    
    public int namesFound = 0;
    public ArrayList<String> playerNames = new ArrayList<>();
    public ArrayList<Integer> playerMatches = new ArrayList<>();
    public ArrayList<String> hashtags = new ArrayList<>();

    @Override
    public void onEnable() {
        // Default Config Options
        config.options().header(
                "The \"everyone-notify\" options toggle whether the @everyone tag will use the features listed.\n" +
                "The \"selftag-notify\" option will use default \"notify\" values if set to true.\n" +
                "The \"title-time\" option below is in server ticks. 20 = 1 second\n" +
                "You can get a full list of sounds at: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html\n" +
                "The MentionMe source code is available at: https://github.com/MoMoe0/MentionMe\n");
        config.addDefault("notify-in-chat", true);
        config.addDefault("notify-in-actionbar", true);
        config.addDefault("notify-in-title", true);
        config.addDefault("everyone-notify-chat", true);
        config.addDefault("everyone-notify-actionbar", true);
        config.addDefault("everyone-notify-title", true);
        config.addDefault("selftag-notify", false);
        config.addDefault("enable-sound", true);
        config.addDefault("mention-color", "&e");
        config.addDefault("hashtag-color", "&b");
        config.addDefault("mention-message", "&eYou were mentioned by &f%player%&e!");
        config.addDefault("title-message", "&eMentioned by:");
        config.addDefault("subtitle-message", "&f%player%");
        config.addDefault("title-time", 50);
        config.addDefault("sound", "ENTITY_EXPERIENCE_ORB_PICKUP");
        config.options().copyDefaults(true);
        saveConfig();
        
        // Register Listeners
        getServer().getPluginManager().registerEvents(new TabComplete(), this);
        if (getServer().getPluginManager().isPluginEnabled("DeluxeChat")) {
            getServer().getPluginManager().registerEvents(new DeluxeChatHook(this), this);
            getLogger().info("Hooked into DeluxeChat!");
        } else {
            getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        }
        
        // Register Commands
        this.getCommand("mentionme").setExecutor(new ReloadCommand(this));
        
        getLogger().info("MentionMe Enabled!");
    }

    @Override
    public void onDisable() {
        // Fired when the server stops and disables all plugins
        reloadConfig();
        saveConfig();

        getLogger().info("MentionMe Disabled!");
    }
}
