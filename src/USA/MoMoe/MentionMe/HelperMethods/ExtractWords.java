package USA.MoMoe.MentionMe.HelperMethods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ExtractWords {

    private int namesFound = 0;
    private ArrayList<String> playerNames = new ArrayList<>();
    private ArrayList<Integer> playerMatches = new ArrayList<>();
    private ArrayList<String> hashtags = new ArrayList<>();
    private ArrayList<String> hashtagData = new ArrayList<>();
    
    void getNames(String message) {
        // Count the matches of @name and extract the name
        Pattern namePattern = Pattern.compile("@([\\w]{3,16})");
        Matcher nameMatcher = namePattern.matcher(message);

        while (nameMatcher.find()) {
            boolean alreadyMatched = false;
            for (String testCase : this.playerNames) {
                if (nameMatcher.group(1).contains(testCase)) {
                    alreadyMatched = true;
                } else if (testCase.contains(nameMatcher.group(1))) {
                    this.playerNames.set(this.playerNames.indexOf(testCase), nameMatcher.group(1));
                    alreadyMatched = true;
                }
            }
            if (!this.playerNames.contains(nameMatcher.group(1)) && !alreadyMatched) {
                this.playerNames.add(nameMatcher.group(1));
                this.namesFound++;
            }
        }
        
        // Add the names to an ArrayList
        for (int i = 0; i < this.namesFound; i++) {
            this.playerMatches.add(i, 0);
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                if (target.getName().toLowerCase().contains(this.playerNames.get(i).toLowerCase())
                        || target.getDisplayName().toLowerCase().contains(this.playerNames.get(i).toLowerCase())) {
                    this.playerMatches.set(i, this.playerMatches.get(i) + 1);
                }
            }
        }
    }
    
    void getHashtags(String message) {
        Pattern hashtagPattern = Pattern.compile("#([\\w]+)");
        Matcher hashtagMatcher = hashtagPattern.matcher(message);

        while (hashtagMatcher.find()) {
            boolean alreadyMatched = false;
            for (String testCase : this.hashtags) {
                if (hashtagMatcher.group(1).toLowerCase().equals(testCase.toLowerCase())) {
                    alreadyMatched = true;
                }
            }
            if (!this.hashtags.contains(hashtagMatcher.group(1)) && !alreadyMatched) {
                this.hashtagData.add(hashtagMatcher.group(1));
            }
            if (!this.hashtags.contains(hashtagMatcher.group(1))) {
                this.hashtags.add(hashtagMatcher.group(1));
            }
        }
    }
    
    int getNamesFound() {
        return this.namesFound;
    }

    ArrayList<String> getPlayerNames() {
        return this.playerNames;
    }

    ArrayList<Integer> getPlayerMatches() {
        return this.playerMatches;
    }

    ArrayList<String> getHashtags() {
        return this.hashtags;
    }
    
    ArrayList<String> getHashtagData() {
        return this.hashtagData;
    }
}
