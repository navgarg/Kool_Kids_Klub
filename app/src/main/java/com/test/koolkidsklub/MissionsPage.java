package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
//
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()){
//                    case R.id.action_profile:
//                        // User clicked on the "Profile" menu option
//                        startActivity(new Intent(MissionsPage.this, ProfileActivity.class));
//                        return true;
//                    case R.id.action_missions:
//                        startActivity(new Intent(MissionsPage.this, MissionsActivity.class));
//                }
//                return true;
//            }
//
//        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // adds menu items to  app bar.
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_profile:
                // User clicked on the "Profile" menu option
                startActivity(new Intent(MissionsPage.this, ProfileActivity.class));
                return true;
            case R.id.action_missions:
                startActivity(new Intent(MissionsPage.this, MissionsActivity.class));
                return true;
            case R.id.action_chat:
                startActivity(new Intent(MissionsPage.this, ChatActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}