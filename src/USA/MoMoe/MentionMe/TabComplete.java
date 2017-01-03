package USA.MoMoe.MentionMe;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import java.util.Collection;

public class TabComplete implements Listener {
    @EventHandler
    public void onPlayerChatTabAttempt(PlayerChatTabCompleteEvent event)
    {
        String token = event.getLastToken();
        Collection<String> autoCompletions;
        String begin;
        if (token.startsWith("@"))
        {
            autoCompletions = event.getTabCompletions();
            autoCompletions.clear();
            begin = token.replaceAll("@", "").toLowerCase();
            for (Player player : Bukkit.getServer().getOnlinePlayers())
            {
                String playerName = player.getName();
                if (playerName.toLowerCase().startsWith(begin)) {
                    autoCompletions.add("@" + playerName);
                }
            }
        }
    }
}
