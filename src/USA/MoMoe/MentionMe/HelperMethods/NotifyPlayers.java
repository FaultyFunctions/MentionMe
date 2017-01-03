package USA.MoMoe.MentionMe.HelperMethods;

import USA.MoMoe.MentionMe.Main;
import io.github.theluca98.textapi.ActionBar;
import io.github.theluca98.textapi.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

class NotifyPlayers {
    @SuppressWarnings("Duplicates")
    void playSoundEveryone(Main plugin, ErrorMessages errorMessages, Player sender) {

        String sound = plugin.getConfig().getString("sound");
        
        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            if (target != sender) {
                try {
                    target.playSound(target.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                } catch (Exception e) {
                    errorMessages.setSoundErrorTrue();
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    void playSound(Main plugin, ErrorMessages errorMessages, ExtractWords extractWords, Player sender) {
        
        String sound = plugin.getConfig().getString("sound");
        
        int j = 0;
        for (int i : extractWords.getPlayerMatches()) {
            if (i == 1) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (target.getName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())
                            || target.getDisplayName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())) {
                        if ((sender != target) || (plugin.getConfig().getBoolean("selftag-notify"))) {
                            try {
                                target.playSound(target.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                            } catch (Exception e) {
                                errorMessages.setSoundErrorTrue();
                            }
                        }
                    }
                }
            }
            j++;
        }
        
    }

    @SuppressWarnings("Duplicates")
    void sendMessageEveryone(Main plugin, Player sender) {

        String mentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mention-message"));
        
        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            if (sender != target) {
                target.sendMessage(mentionMessage.replace("%player%", sender.getDisplayName()));
            }
        }
    }

    @SuppressWarnings("Duplicates")
    void sendMessage(Main plugin, ExtractWords extractWords, Player sender) {
        
        String mentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mention-message"));
        
        int j = 0;
        for (int i : extractWords.getPlayerMatches()) {
            if (i == 1) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (target.getName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())
                            || target.getDisplayName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())) {
                        if (sender != target || plugin.getConfig().getBoolean("selftag-notify")) {
                            target.sendMessage(mentionMessage.replace("%player%", sender.getDisplayName()));
                        }
                    }
                }
            }
            j++;
        }
    }

    @SuppressWarnings("Duplicates")
    void sendTitleEveryone(Main plugin, Player sender) {

        String titleMentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("title-message"));
        String subtitleMentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("subtitle-message"));
        Title title = new Title(
                titleMentionMessage.replace("%player%", sender.getDisplayName()),
                subtitleMentionMessage.replace("%player%", sender.getDisplayName()),
                2, plugin.getConfig().getInt("title-time"), 10);

        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            if (sender != target) {
                title.send(target);
            }
        }
    }

    @SuppressWarnings("Duplicates")
    void sendTitle(Main plugin, ExtractWords extractWords, Player sender) {
        
        String titleMentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("title-message"));
        String subtitleMentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("subtitle-message"));
        Title title = new Title(
                titleMentionMessage.replace("%player%", sender.getDisplayName()),
                subtitleMentionMessage.replace("%player%", sender.getDisplayName()),
                2, plugin.getConfig().getInt("title-time"), 10);

        int j = 0;
        for (int i : extractWords.getPlayerMatches()) {
            if (i == 1) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (target.getName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())
                            || target.getDisplayName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())) {
                        if (sender != target || plugin.getConfig().getBoolean("selftag-notify")) {
                            title.send(target);
                        }
                    }
                }
            }
            j++;
        }
    }

    @SuppressWarnings("Duplicates")
    void sendActionEveryone(Main plugin, Player sender) {

        String titleMentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("title-message"));
        String subtitleMentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("subtitle-message"));
        Title title = new Title(
                titleMentionMessage.replace("%player%", sender.getDisplayName()),
                subtitleMentionMessage.replace("%player%", sender.getDisplayName()),
                2, plugin.getConfig().getInt("title-time"), 10);

        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            if (sender != target) {
                title.send(target);
            }
        }
    }

    @SuppressWarnings("Duplicates")
    void sendAction(Main plugin, ExtractWords extractWords, Player sender) {

        String mentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mention-message"));
        ActionBar bar = new ActionBar(mentionMessage.replace("%player%", sender.getDisplayName()));

        int j = 0;
        for (int i : extractWords.getPlayerMatches()) {
            
            if (i == 1) {
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (target.getName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())
                            || target.getDisplayName().toLowerCase().contains(extractWords.getPlayerNames().get(j).toLowerCase())) {
                        if (sender != target || plugin.getConfig().getBoolean("selftag-notify")) {
                            bar.send(target);
                        }
                    }
                }
            }
            j++;
        }
    }
}
