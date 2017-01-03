package USA.MoMoe.MentionMe.Hooks;

import USA.MoMoe.MentionMe.HelperMethods.CaseManager;
import USA.MoMoe.MentionMe.HelperMethods.VentureChatColorSelector;
import USA.MoMoe.MentionMe.Main;
import mineverse.Aust1n46.chat.api.MineverseChatAPI;
import mineverse.Aust1n46.chat.api.MineverseChatPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class VentureChatHook implements Listener {

    private Main plugin;

    public VentureChatHook(Main instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onVentureChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player sender = event.getPlayer();
        VentureChatColorSelector vccs = new VentureChatColorSelector();

        MineverseChatPlayer mcp = MineverseChatAPI.getOnlineMineverseChatPlayer(sender);
        String chatColor = vccs.colorSelect(mcp.getCurrentChannel().getChatColor());

        CaseManager caseManager = new CaseManager();
        message = caseManager.MentionMeManager(plugin, sender, message, chatColor);
        event.setMessage(message);
    }
}
