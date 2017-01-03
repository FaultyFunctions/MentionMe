package USA.MoMoe.MentionMe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    private Main plugin;

    ReloadCommand (Main instance) {
        plugin = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("mentionme.reload")) {
                sender.sendMessage(ChatColor.YELLOW + "MentionMe reloaded!");
                plugin.reloadConfig();
                plugin.saveConfig();
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use /mentionme");
                return false;
            }
        } else {
            plugin.reloadConfig();
            plugin.saveConfig();
            plugin.getLogger().info("MentionMe reloaded!");
            return true;
        }
    }
}
