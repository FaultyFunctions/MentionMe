package USA.MoMoe.MentionMe;

import USA.MoMoe.MentionMe.HelperMethods.CaseManager;
import me.clip.deluxechat.events.DeluxeChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeluxeChatHook implements Listener {

    private Main plugin;

    DeluxeChatHook(Main instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onDeluxeChat(DeluxeChatEvent event) {
        String message = event.getChatMessage();
        Player sender = event.getPlayer();
        CaseManager caseManager = new CaseManager();
        message = caseManager.MentionMeManager(plugin, sender, message, event.getDeluxeFormat().getChatColor());
        event.setChatMessage(message);
    }
}
