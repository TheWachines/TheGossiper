package wchines.com.thegossiper.service;

import wchines.com.thegossiper.model.UserProfile;
import wchines.com.thegossiper.model.facebook.FriendList;
import wchines.com.thegossiper.model.request.Query;
import wchines.com.thegossiper.model.request.QueryResponse;

/**
 * Created by Icaio on 27/10/2016.
 */

public interface SocialMediaService<T> {

    long getToken();

    long getSessionId();

    FriendList getFriendList();

    UserProfile getUserProfile();

    QueryResponse<T> query(Query query);
}
