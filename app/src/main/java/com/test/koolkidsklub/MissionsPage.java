package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.FirebaseAuth;

public class MissionsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missions_page);

        Intent intent = getIntent();
        String receivedName = intent.getStringExtra("name");

        setTitle(receivedName);

        Log.d("MissionsPage", "Intent received");
        Log.d("MissionsPage", "name: " + intent.getStringExtra("name") + "Desc: " + intent.getStringExtra("long_desc"));


        TextView name = findViewById(R.id.mission_page_name);
        name.setText(receivedName);

        TextView long_desc = findViewById(R.id.mission_page_long_desc);
        long_desc.setText(intent.getStringExtra("long_desc"));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.action_missions).setChecked(true);
        navigation.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_profile:
                        // User clicked on the "Profile" menu option
                        startActivity(new Intent(MissionsPage.this, ProfileActivity.class));
                        finish();
                        break;
                    case R.id.action_missions:
                        startActivity(new Intent(MissionsPage.this, MissionsActivity.class));
                        finish();
                        break;
                    case R.id.action_chat:
                        startActivity(new Intent(MissionsPage.this, ChatActivity.class));
                        break;
                    case R.id.action_goto_list:

                        startActivity(new Intent(MissionsPage.this, ListActivity.class));
                        finish();
                        break;
                    case R.id.action_map:
                        startActivity(new Intent(MissionsPage.this, MapsActivity.class));
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
            startActivity(new Intent(MissionsPage.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}