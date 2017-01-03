package USA.MoMoe.MentionMe.HelperMethods;

import USA.MoMoe.MentionMe.Main;
import org.bukkit.entity.Player;

public class CaseManager {
    public String MentionMeManager(Main plugin, Player sender, String message, String chatColor) {
        
        ModifyChat modifyChat = new ModifyChat();
        NotifyPlayers notifyPlayers = new NotifyPlayers();
        ErrorMessages errorMessages = new ErrorMessages();
        ExtractWords extractWords = new ExtractWords();
        extractWords.getNames(message);
        extractWords.getHashtags(message);
        
        // Everyone Case
        if (message.toLowerCase().contains("@everyone") && sender.hasPermission("mentionme.everyone")) {
            
            message = modifyChat.everyoneMention(plugin, message, chatColor);
            
            if (plugin.getConfig().getBoolean("enable-sound")) {
                notifyPlayers.playSoundEveryone(plugin, errorMessages, sender);
            }
            
            if (plugin.getConfig().getBoolean("everyone-notify-chat")) {
                notifyPlayers.sendMessageEveryone(plugin, sender);
            }
            
            if (plugin.getConfig().getBoolean("everyone-notify-title")) {
                notifyPlayers.sendTitleEveryone(plugin, sender);
            }
            
            if (plugin.getConfig().getBoolean("everyone-notify-actionbar")) {
                notifyPlayers.sendActionEveryone(plugin, sender);
            }
            
        } else if (message.toLowerCase().contains("@everyone") && !sender.hasPermission("mentionme.everyone")) {
            errorMessages.setNoPermsTrue();
        } else {
            // Normal Case
            
            message = modifyChat.normalMention(plugin, extractWords, message, chatColor);
            
            if (plugin.getConfig().getBoolean("enable-sound")) {
                notifyPlayers.playSound(plugin, errorMessages, extractWords, sender);
            }
            
            if (plugin.getConfig().getBoolean("notify-in-chat")) {
                notifyPlayers.sendMessage(plugin, extractWords, sender);
            }
            
            if (plugin.getConfig().getBoolean("notify-in-title")) {
                notifyPlayers.sendTitle(plugin, extractWords, sender);
            }
            
            if (plugin.getConfig().getBoolean("notify-in-actionbar")) {
                notifyPlayers.sendAction(plugin, extractWords, sender);
            }
        }
        
        // Hashtags
        if (message.toLowerCase().contains("#")) {
            message = modifyChat.hashtag(plugin, extractWords, message, chatColor);
        }
        
        // Error Checking
        if (errorMessages.getNoPerms()) {
            errorMessages.NoPerms(sender);
        }
        
        if (errorMessages.getSoundError()) {
            errorMessages.SoundError(plugin, sender);
        }
        
        for (int i : extractWords.getPlayerMatches()) {
            if (i > 1) {
                errorMessages.setMultipleMatchesTrue();
            }
        }
        
        if (errorMessages.getMultipleMatches()) {
            errorMessages.MultipleMatches(sender);
        }
        
        return message;
    }
}
