package wchines.com.thegossiper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wchines.com.thegossiper.model.facebook.FacebookFriend;
import wchines.com.thegossiper.model.facebook.FriendList;

public class gossipCreator extends AppCompatActivity {


    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gossip_creator);
        callbackManager = CallbackManager.Factory.create();

        if(Profile.getCurrentProfile() != null){
            ProfilePictureView profilePictureView;
            profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
            profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
            Spinner  spinnerSource = (Spinner) findViewById(R.id.friendSource);
            Spinner  spinnerDestiny = (Spinner) findViewById(R.id.friendDestiny);
            List<FacebookFriend> friendList = getFriendList();

            ArrayAdapter<FacebookFriend> dataAdapter = new ArrayAdapter<FacebookFriend>(this,
                    android.R.layout.simple_spinner_item, friendList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSource.setAdapter(dataAdapter);
            spinnerDestiny.setAdapter(dataAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private List<FacebookFriend> getFriendList(){

        final List<FacebookFriend> spinnerArray =  new ArrayList<FacebookFriend>();

        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONObject friendListJson = response.getJSONObject().getJSONObject("data");
                            ObjectMapper mapper = new ObjectMapper();
                            FriendList friendList = mapper.readValue(friendListJson.toString(), FriendList.class);
                            spinnerArray.addAll(friendList.getFriendList());
                        }catch (JSONException e){
                            System.out.println("JSONEXCEPTION");
                        }catch (IOException e){
                            System.out.println("IOException...");
                        }
                    }
                }
        ).executeAsync();


        return spinnerArray;
    }




}
