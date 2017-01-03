package USA.MoMoe.MentionMe.HelperMethods;

import USA.MoMoe.MentionMe.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

class ModifyChat {
    
    String everyoneMention(Main plugin, String message, String chatColor) {
        
        String mentionColor = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mention-color"));
        
        message = message.replaceAll("@" + "(?i)" + "everyone", mentionColor + "@" + "Everyone" + chatColor + ChatColor.getLastColors(message));
        
        return message;
    }
    
    String normalMention(Main plugin, ExtractWords extractWords, String message, String chatColor) {

        String mentionColor = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mention-color"));
        
        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            for (int i = 0; i < extractWords.getNamesFound(); i++) {
                if ((target.getName().toLowerCase().contains(extractWords.getPlayerNames().get(i).toLowerCase())
                        || target.getDisplayName().toLowerCase().contains(extractWords.getPlayerNames().get(i).toLowerCase()))
                        && extractWords.getPlayerMatches().get(i) == 1) {
                    message = message.replaceAll("@" + "(?i)" + extractWords.getPlayerNames().get(i) + "[\\w]*",
                            mentionColor + "@" + target.getName() + chatColor + ChatColor.getLastColors(message));
                    
                }
            }
        }
        
        return message;
    }
    
    String hashtag(Main plugin, ExtractWords extractWords, String message, String chatColor) {

        String hashtagColor = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("hashtag-color"));
        
        for (String hashString : extractWords.getHashtags()) {
            message = message.replaceAll("(#\\b" + hashString + "\\b)", hashtagColor + "#" + hashString + chatColor + ChatColor.getLastColors(message));
        }
        
        return message;
    }
}
