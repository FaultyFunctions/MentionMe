package USA.MoMoe.MentionMe.Hooks;

import USA.MoMoe.MentionMe.HelperMethods.CaseManager;
import USA.MoMoe.MentionMe.Main;
import me.clip.deluxechat.events.DeluxeChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeluxeChatHook implements Listener {

    private Main plugin;

    public DeluxeChatHook(Main instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onDeluxeChat(DeluxeChatEvent event) {
        String message = event.getChatMessage();
        Player sender = event.getPlayer();
        CaseManager caseManager = new CaseManager();
        message = caseManager.MentionMeManager(plugin, sender, message, ChatColor.getLastColors(event.getDeluxeFormat().getChatColor()));
        event.setChatMessage(message);
    }
}
