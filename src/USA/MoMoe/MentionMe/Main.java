package USA.MoMoe.MentionMe;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    // Enable the config object
    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Default Config Options
        config.options().header(
                "Thank you for downloading MentionMe v0.3.0!\n" +
                "The \"title-time\" option below is in server ticks. 20 = 1 second\n" +
                "You can get a full list of sounds at: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html\n" +
                "The MentionMe source code is available at: https://github.com/MoMoe0/MentionMe\n");
        config.addDefault("notify-in-chat", true);
        config.addDefault("notify-in-actionbar", true);
        config.addDefault("notify-in-title", true);
        config.addDefault("everyone-notify-chat", true);
        config.addDefault("everyone-notify-actionbar", true);
        config.addDefault("everyone-notify-title", true);
        config.addDefault("enable-sound", true);
        config.addDefault("mention-color", "&e");
        config.addDefault("hashtag-color", "&b");
        config.addDefault("mention-message", "&aYou were mentioned by %player%&a !");
        config.addDefault("title-message", "&aMentioned by:");
        config.addDefault("subtitle-message", "%player%");
        config.addDefault("title-time", 50);
        config.addDefault("sound", "ENTITY_EXPERIENCE_ORB_PICKUP");
        config.options().copyDefaults(true);
        saveConfig();

        // Register Listener
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        
        // Register Commands
        this.getCommand("mentionmereload").setExecutor(new ReloadCommand(this));
        
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
