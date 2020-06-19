package com.test.koolkidsklub;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserLoader extends AsyncTaskLoader<List<UserInfo>> {

    UserLoader(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<UserInfo> loadInBackground() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

        final ArrayList<UserInfo> list = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UserInfo user = snapshot.getValue(UserInfo.class);
                    list.add(user);
                    Log.d("UserLoader", "User added");
                }
                ListActivity.mAdapter.notifyDataSetChanged();
                Log.d("UserLoader", "Adapter notified");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("UserLoader", "in onCancelled()");

            }
        });

        Log.d("UserLoader", " loadInBackground() completed");
        return list;
    }
}
