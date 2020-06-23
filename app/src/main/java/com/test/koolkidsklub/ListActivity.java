package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static UserInfoAdapter mAdapter;

    public static final ArrayList<UserInfo> list = new ArrayList<>();
    //public static ProgressBar progressBar;
    TextView mEmptyStateTextView;
    private static final int USER_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView) findViewById(R.id.list_view);
        mAdapter = new UserInfoAdapter(this, list);
        listView.setAdapter(mAdapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.action_goto_list).setChecked(true);
        navigation.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_profile:
                        // User clicked on the "Profile" menu option
                        startActivity(new Intent(ListActivity.this, ProfileActivity.class));
                        finish();
                        break;
                    case R.id.action_missions:
                        startActivity(new Intent(ListActivity.this, MissionsActivity.class));
                        finish();
                        break;
                    case R.id.action_chat:
                        startActivity(new Intent(ListActivity.this, ChatActivity.class));
                        break;
                    case R.id.action_goto_list:
                        startActivity(new Intent(ListActivity.this, ListActivity.class));
                        finish();
                        break;
                    case R.id.action_map:
                        startActivity(new Intent(ListActivity.this, MapsActivity.class));
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
            startActivity(new Intent(ListActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
