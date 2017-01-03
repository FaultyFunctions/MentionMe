package USA.MoMoe.MentionMe;

import USA.MoMoe.MentionMe.HelperMethods.CaseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Main plugin;

    ChatListener(Main instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player sender = event.getPlayer();
        CaseManager caseManager = new CaseManager();
        message = caseManager.MentionMeManager(plugin, sender, message, ChatColor.getLastColors(event.getFormat()));
        event.setMessage(message);
    }
}