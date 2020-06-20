package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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



        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String email =user.getEmail();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("User");
        usersRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
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
}