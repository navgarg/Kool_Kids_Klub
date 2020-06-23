package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView phone_text = findViewById(R.id.profile_phone);
        final TextView name_text = findViewById(R.id.profile_name);
        final TextView email_text = findViewById(R.id.profile_email);
        final TextView lat_text = findViewById(R.id.profile_lat);
        final TextView long_text = findViewById(R.id.profile_long);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null) {

            String email = user.getEmail();
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("User");
            usersRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        phone_text.setText(datas.child("phoneNumber").getValue().toString());
                        name_text.setText(datas.child("name").getValue().toString());
                        email_text.setText(datas.child("email").getValue().toString());
                        lat_text.setText(datas.child("lat").getValue().toString());
                        long_text.setText(datas.child("long").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }
        else{
            Toast.makeText(ProfileActivity.this, "Kindly sign in", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));

        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.action_profile).setChecked(true);
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_profile:
                        // User clicked on the "Profile" menu option
                        startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                        finish();
                        break;
                    case R.id.action_missions:
                        startActivity(new Intent(ProfileActivity.this, MissionsActivity.class));
                        finish();
                        break;
                    case R.id.action_chat:
                        startActivity(new Intent(ProfileActivity.this, ChatActivity.class));
                        finish();
                        break;
                    case R.id.action_goto_list:
                        startActivity(new Intent(ProfileActivity.this, ListActivity.class));
                        finish();
                        break;
                    case R.id.action_map:
                        startActivity(new Intent(ProfileActivity.this, MapsActivity.class));
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // adds menu items to  app bar.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_signout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}