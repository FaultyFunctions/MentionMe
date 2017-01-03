package USA.MoMoe.MentionMe.HelperMethods;

import org.bukkit.ChatColor;

public class VentureChatColorSelector {
    public String colorSelect(String chatColor) {
        switch (chatColor.toLowerCase()) {
            case "aqua":
                chatColor = ChatColor.AQUA.toString();
                break;
            case "black":
                chatColor = ChatColor.BLACK.toString();
                break;
            case "blue":
                chatColor = ChatColor.BLUE.toString();
                break;
            case "dark_aqua":
                chatColor = ChatColor.DARK_AQUA.toString();
                break;
            case "dark_blue":
                chatColor = ChatColor.DARK_BLUE.toString();
                break;
            case "dark_grey":
                chatColor = ChatColor.DARK_GRAY.toString();
                break;
            case "dark_gray":
                chatColor = ChatColor.DARK_GRAY.toString();
                break;
            case "dark_green":
                chatColor = ChatColor.DARK_GREEN.toString();
                break;
            case "dark_purple":
                chatColor = ChatColor.DARK_PURPLE.toString();
                break;
            case "dark_red":
                chatColor = ChatColor.DARK_RED.toString();
                break;
            case "gold":
                chatColor = ChatColor.GOLD.toString();
                break;
            case "orange":
                chatColor = ChatColor.GOLD.toString();
                break;
            case "grey":
                chatColor = ChatColor.GRAY.toString();
                break;
            case "gray":
                chatColor = ChatColor.GRAY.toString();
                break;
            case "green":
                chatColor = ChatColor.GREEN.toString();
                break;
            case "light_purple":
                chatColor = ChatColor.LIGHT_PURPLE.toString();
                break;
            case "red":
                chatColor = ChatColor.RED.toString();
                break;
            case "white":
                chatColor = ChatColor.WHITE.toString();
                break;
            case "yellow":
                chatColor = ChatColor.YELLOW.toString();
                break;
            default:
                chatColor = ChatColor.WHITE.toString();
        }
        
        return chatColor;
    }
}
