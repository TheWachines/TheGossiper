package wchines.com.thegossiper.service.Implementation;

import wchines.com.thegossiper.model.UserProfile;
import wchines.com.thegossiper.model.facebook.FriendList;
import wchines.com.thegossiper.model.request.Query;
import wchines.com.thegossiper.model.request.QueryResponse;
import wchines.com.thegossiper.service.SocialMediaService;

/**
 * Created by IcaioLocal on 27/10/2016.
 */

public class FacebookSocialMediaImplementation implements SocialMediaService {
    @Override
    public long getToken() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getSessionId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FriendList getFriendList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserProfile getUserProfile() {
        throw new UnsupportedOperationException();
    }

    @Override
    public QueryResponse query(Query query) {
        throw new UnsupportedOperationException();
    }
}
