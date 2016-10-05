package wchines.com.thegossiper.model.facebook;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}