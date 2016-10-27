package wchines.com.thegossiper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wchines.com.thegossiper.model.facebook.FacebookFriend;

public class gossipCreator extends AppCompatActivity {


    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;

    AccessToken currentAccToken;

    ProfilePictureView profilePictureView;
    Spinner spinnerSource ;
    Spinner spinnerDestiny ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        callbackManager = CallbackManager.Factory.create();
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                setProfile(currentProfile);
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // On AccessToken changes fetch the new profile which fires the event on
                // the ProfileTracker if the profile is different
                Profile.fetchProfileForCurrentAccessToken();
                currentAccToken = currentAccessToken;
            }
        };

        // Ensure that our profile is up to date
        Profile.fetchProfileForCurrentAccessToken();



        setContentView(R.layout.activity_gossip_creator);
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);

        spinnerSource = (Spinner) findViewById(R.id.friendSource);
        spinnerDestiny = (Spinner) findViewById(R.id.friendDestiny);

//        spinnerSource.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(spinnerSource.getAdapter() == null){
//                    setFriendDropDown();
//                }
//            }
//        });

                LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList
                ("user_friends", "email" , "user_photos" ));


        if( Profile.getCurrentProfile() != null) {
            setFriendDropDown();
        }

    }


    private void setProfile(Profile currentProfile) {
        if (currentProfile != null) {

            profilePictureView.setProfileId(currentProfile.getId());

            setFriendDropDown();
        }

    }

    private void setFriendDropDown(){
        List<FacebookFriend> friendList = getFriendList();

        ArrayAdapter<FacebookFriend> dataAdapter = new ArrayAdapter<FacebookFriend>(this,
                android.R.layout.simple_spinner_item, friendList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(dataAdapter);
        spinnerDestiny.setAdapter(dataAdapter);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private List<FacebookFriend> getFriendList() {

        final List<FacebookFriend> spinnerList = new ArrayList<FacebookFriend>();

        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                    }
                }
        ).executeAsync();

        GraphRequest request = GraphRequest.newMyFriendsRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray array, GraphResponse response) {
                        try {
                            JSONArray friendListJson = response.getJSONObject().getJSONArray("data");
                            for(int i = 0 ; i< friendListJson.length() ; i++){
                                spinnerList.add(new FacebookFriend(friendListJson.getString(i)));
                            }
                        } catch (JSONException e) {
                            System.out.println("JSONEXCEPTION.");
                        }

                    }
                }
        );

        request.executeAsync();


        return spinnerList;
    }


}
