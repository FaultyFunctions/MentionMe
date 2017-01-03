package USA.MoMoe.MentionMe.HelperMethods;

import USA.MoMoe.MentionMe.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

class ErrorMessages {
    
    private boolean noPerms = false;
    private boolean soundError = false;
    private boolean multipleMatches = false;
    
    void NoPerms(Player sender) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use that.");
    }
    
    void SoundError(Main plugin, Player sender) {
        sender.sendMessage(ChatColor.RED + "[MentionMe] ERROR WITH SOUNDNAME: Please contact an administrator");
        plugin.getLogger().info("Error with the sound name!");
    }
    
    void MultipleMatches(Player sender) {
        sender.sendMessage(ChatColor.RED + "Your mention matched two or more players!");
    }

    void setNoPermsTrue() {
        this.noPerms = true;
    }

    void setSoundErrorTrue() {
        this.soundError = true;
    }

    void setMultipleMatchesTrue() {
        this.multipleMatches = true;
    }

    boolean getNoPerms() {
        return noPerms;
    }

    boolean getSoundError() {
        return soundError;
    }

    boolean getMultipleMatches() {
        return multipleMatches;
    }
}
