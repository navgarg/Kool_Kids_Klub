package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
                    Log.d("MissionsActivity", "MissionsInfo: " + missionsInfo  + "List: " + list_mission);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MissionsActivity", "in onCancelled()");

            }
        });

        missions_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(MissionsActivity.this, MissionsPage.class);
                intent.putExtra("long_desc", adapter.getItem(pos).getLong_desc());
                intent.putExtra("name", adapter.getItem(pos).getname());
                startActivity(intent);
                Log.d("MissionsActivity", "Intent sent");

            }
        });



    }
}