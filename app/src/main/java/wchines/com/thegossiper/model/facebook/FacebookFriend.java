package wchines.com.thegossiper.model.facebook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacebookFriend {
    private String facebookId;

    private String name;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public FacebookFriend(String facebookId, String name) {
        this.facebookId = facebookId;
        this.name = name;
    }

    /**
     * TODO: need to fix this constructor. Use regexp.
     * @param rawResponse
     */
    public FacebookFriend(String rawResponse) {
        Matcher nameMatcher = Pattern.compile("\"name\": \"(.*)\"").matcher(rawResponse);
        Matcher idMatcher = Pattern.compile("\"id\": \"(.*)\"").matcher(rawResponse);
        if(nameMatcher.find()){
            this.name = nameMatcher.group();
        }else {
            this.name = "Not found";
        }
        if(idMatcher.find()){
            this.facebookId = idMatcher.group();
        }else {
            this.facebookId = "not found";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}