package USA.MoMoe.MentionMe;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    // Enable the config object
    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Default Config Options
        config.addDefault("NotifyInChat", true);
        config.addDefault("NotifyInActionBar", true);
        config.addDefault("MentionColor", "&e");
        config.addDefault("HashtagColor", "&d");
        config.addDefault("MentionMessage", "&aYou were mentioned by %player%&a !");
        config.addDefault("Sound", "ENTITY_EXPERIENCE_ORB_PICKUP");
        config.options().copyDefaults(true);
        saveConfig();

        // Register Listener
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        
        // Register Commands
        this.getCommand("mentionmereload").setExecutor(new ReloadCommand(this));
        
        getLogger().info(ChatColor.GREEN + "MentionMe Enabled!");
    }

    @Override
    public void onDisable() {
        // Fired when the server stops and disables all plugins
        reloadConfig();
        saveConfig();

        getLogger().info(ChatColor.RED + "MentionMe Enabled!");
    }
}
