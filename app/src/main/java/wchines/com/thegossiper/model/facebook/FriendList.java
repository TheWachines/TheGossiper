package wchines.com.thegossiper.model.facebook;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IcaioLocal on 22/09/2016.
 */

public class FriendList {

    List<FacebookFriend> friendList;

    public FriendList(){}

    public FriendList(FacebookFriend[] friendArray){
        friendList = Arrays.asList(friendArray);
    }

    public List<FacebookFriend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FacebookFriend> friendList) {
        this.friendList = friendList;
    }

    public void setFriendList(FacebookFriend[] friendArray){
        friendList = Arrays.asList(friendArray);
    }

    public void addFriendList(FacebookFriend[] friendArray){
        friendList.addAll(Arrays.asList(friendArray));
    }


}
