package com.test.koolkidsklub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    }
}