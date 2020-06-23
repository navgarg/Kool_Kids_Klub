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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.test.koolkidsklub.ListActivity.list;

public class MissionsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missions);


        final ArrayList<MissionsInfo> list_mission = new ArrayList<>();
        Log.d("MissionsActivity", "List_missions: " + list_mission);

        FloatingActionButton fab = findViewById(R.id.fab_missions);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MissionsActivity.this, AddMission.class));
            }
        });

        ListView missions_list_view = (ListView) findViewById(R.id.missions_list_view);
        final MissionsInfoAdapter adapter = new MissionsInfoAdapter(this, list_mission);
        missions_list_view.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Missions");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_mission.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MissionsInfo missionsInfo = snapshot.getValue(MissionsInfo.class);
                    list_mission.add(missionsInfo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        missions_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(MissionsActivity.this, MissionsPage.class);
                intent.putExtra("long_desc", adapter.getItem(pos).getLong_desc());
                intent.putExtra("name", adapter.getItem(pos).getname());
                startActivity(intent);

            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.action_missions).setChecked(true);
        navigation.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_profile:
                        startActivity(new Intent(MissionsActivity.this, ProfileActivity.class));
                        finish();
                        break;
                    case R.id.action_missions:
                        startActivity(new Intent(MissionsActivity.this, MissionsActivity.class));
                        finish();
                        break;
                    case R.id.action_chat:
                        startActivity(new Intent(MissionsActivity.this, ChatActivity.class));
                        break;
                    case R.id.action_goto_list:

                        startActivity(new Intent(MissionsActivity.this, ListActivity.class));
                        finish();
                        break;
                    case R.id.action_map:
                        startActivity(new Intent(MissionsActivity.this, MapsActivity.class));
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
            startActivity(new Intent(MissionsActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}