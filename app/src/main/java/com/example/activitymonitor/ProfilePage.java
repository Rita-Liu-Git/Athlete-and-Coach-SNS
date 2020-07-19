package com.example.activitymonitor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Placeholder Activity that allows the user to sign out, will show user info later
 */
public class ProfilePage extends AppCompatActivity {

    LinearLayout sendMessageButton;

    /**
     * When the activity is loaded add listeners and functions to buttons, if user submits we sign them out
     * @param savedInstanceState the instance of the user variable used for access
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        /*TextView newName = (TextView) findViewById(R.id.name);
        newName.setText("Default Name");*/

        Button confirmExerciseButton = findViewById(R.id.buttonSignOut);
        confirmExerciseButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                SignOut();
            }
        });

        /*
        If user clicks on "Send a message" button, redirect to CommentsPage
        (Will)
         */
        sendMessageButton = findViewById(R.id.send_message_button);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(ProfilePage.this, SendMessageActivity.class);

                // Get user profile to pass to CommentsPage
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                msgIntent.putExtra("USERID", currentUser.getUid());
                startActivity(msgIntent);
            }
        });
    }

    /**
     * Allows the user to sign out and directs to the signin activity
     */
    private void SignOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, SignIn.class);
        this.startActivity(intent);
        this.finishAffinity();
    }
}
