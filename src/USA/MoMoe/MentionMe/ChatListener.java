package USA.MoMoe.MentionMe;

import io.github.theluca98.textapi.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener {

    private Main plugin;

    ChatListener(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String newMessage = event.getMessage();

        // Count the matches of @name and extract the name
        int namesFound = 0;
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<Integer> playerMatches = new ArrayList<>();
        Pattern pattern = Pattern.compile("@([\\w]{3,16})");
        Matcher matcher = pattern.matcher(newMessage);

        while (matcher.find()) {
            if (!playerNames.contains(matcher.group(1))) {
                playerNames.add(matcher.group(1));
                namesFound++;
            }
        }
        
        // Add the names to an ArrayList
        for (int i = 0; i < namesFound; i++) {
            playerMatches.add(i, 0);
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                if (target.getName().toLowerCase().contains(playerNames.get(i).toLowerCase())
                        || target.getDisplayName().toLowerCase().contains(playerNames.get(i).toLowerCase())) {
                    playerMatches.set(i, playerMatches.get(i) + 1);
                }
            }
        }
        
        boolean multipleMatches = false;
        
        // Multiple matches error checking
        for (int matchAmt : playerMatches) {
            //noinspection RedundantConditionalExpression
            multipleMatches = matchAmt > 1 ? true : false;
        }

        Player sender = event.getPlayer();
        
        // Send a message if multiple matches are found
        if (multipleMatches) {
            sender.sendMessage(ChatColor.RED + "Your mention matched more than one player, please be more specific!");
        }
        
        String mentionColor = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MentionColor"));
        String mentionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MentionMessage"));
        String sound = plugin.getConfig().getString("Sound");
        ActionBar bar = new ActionBar(mentionMessage.replace("%player%", sender.getDisplayName() + mentionColor));

        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            // @Everyone Mention Case
            if (newMessage.toLowerCase().contains("@everyone") && sender.hasPermission("mentionme.everyone")) {
                // Color the @Everyone tag
                newMessage = newMessage.replaceAll("@" + "(?i)" + "everyone", mentionColor + "@" + "Everyone" + ChatColor.RESET);
                event.setMessage(newMessage);

                // Send a sound to everyone
                try {
                    target.playSound(target.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                } catch (Exception e) {
                    plugin.getLogger().info("Error with the sound name.");
                }

                // Notify all players in chat
                if (plugin.getConfig().getBoolean("NotifyInChat")) {
                    target.sendMessage(mentionMessage.replace("%player%", sender.getDisplayName() + mentionColor));
                }

                // Notify all players via actionbar
                if (plugin.getConfig().getBoolean("NotifyInActionBar")) {
                    bar.send(target);
                }

            } else if (newMessage.toLowerCase().contains("@everyone") && !sender.hasPermission("mentionme.everyone")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use @Everyone.");
            } else {
                // Normal Mention Case
                for (int i = 0; i < namesFound; i++) {
                    if ((target.getName().toLowerCase().contains(playerNames.get(i).toLowerCase())
                            || target.getDisplayName().toLowerCase().contains(playerNames.get(i).toLowerCase()))
                            && playerMatches.get(i) == 1) {
                        // Color @name tags
                        newMessage = newMessage.replaceAll("@" + "(?i)" + playerNames.get(i),
                                mentionColor + "@" + target.getName() + ChatColor.RESET);
                        event.setMessage(newMessage);

                        // Send a sound to the target
                        try {
                            target.playSound(target.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                        } catch (Exception e) {
                            plugin.getLogger().info("Error with the sound name.");
                        }

                        // Notify the player in chat
                        if (plugin.getConfig().getBoolean("NotifyInChat")) {
                            target.sendMessage(mentionMessage.replace("%player%", sender.getDisplayName() + mentionColor));
                        }

                        // Notify the player via actionbar
                        if (plugin.getConfig().getBoolean("NotifyInActionBar")) {
                            bar.send(target);
                        }
                    }
                }
            }
        }
        
        // Hashtag System
    }

    /*@EventHandler
    public void onServerChat(ServerCommandEvent event) {
        String command = event.getCommand();
    }*/
}