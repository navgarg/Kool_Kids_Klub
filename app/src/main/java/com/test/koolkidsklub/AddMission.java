package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mission);

        final EditText name_edit = findViewById(R.id.edit_text_name);
        final EditText short_desc_edit = findViewById(R.id.edit_short_desc);
        final EditText long_desc_edit = findViewById(R.id.edit_long_desc);
        TextView add_mission = findViewById(R.id.add_mission);

        add_mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!name_edit.getText().toString().equals("")
                        && !short_desc_edit.getText().toString().equals("")
                        && !long_desc_edit.getText().toString().equals("")) {

                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Missions")
                            .push()
                            .setValue(new MissionsInfo(name_edit.getText().toString(), short_desc_edit.getText().toString(), long_desc_edit.getText().toString()));

                    name_edit.setText("");
                    short_desc_edit.setText("");
                    long_desc_edit.setText("");

                    Toast.makeText(AddMission.this, "Mission added successfully!", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(AddMission.this, MissionsActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(AddMission.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                startActivity(new Intent(AddMission.this, ProfileActivity.class));
                return true;
            case R.id.action_missions:
                startActivity(new Intent(AddMission.this, MissionsActivity.class));
                return true;
            case R.id.action_chat:
                startActivity(new Intent(AddMission.this, ChatActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}