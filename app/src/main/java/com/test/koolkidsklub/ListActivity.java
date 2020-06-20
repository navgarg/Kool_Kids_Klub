package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
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


//        //Get a reference to ConnectivityManager to check state of network connectivity.
//        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

//        //Get details on currently active default data network
//        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

//        //if there is a network connection, fetch the data
//        if(networkInfo != null && networkInfo.isConnected()) {

        // Initialise the LoaderManager and call it to start loading the network request on the background thread.
//            getLoaderManager().initLoader(USER_LOADER_ID, null, this);
//
//            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
//            listView.setEmptyView(mEmptyStateTextView);
//        }else {
//            // Otherwise, display error
//            // First, hide loading indicator so error message will be visible
//            View loadingIndicator = findViewById(R.id.loader);
//            loadingIndicator.setVisibility(View.GONE);
//            mEmptyStateTextView.setText(R.string.no_internet_connection);
//        }
//
//    }
//
//    @Override
//    public Loader<List<UserInfo>> onCreateLoader(int i, Bundle bundle) {
//        // Create a new loader for the given URL
//        return new UserLoader(this);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<UserInfo>> loader, List<UserInfo> userInfos) {
//        if (userInfos!= null && !userInfos.isEmpty()) {
//            Log.d("ListActivity", "in onLoadFinished() if");
//            progressBar.setVisibility(View.GONE);
//        }
//        else {
//            // Set empty state text to display "No users found."
////            mEmptyStateTextView.setText(R.string.no_users);
//            progressBar = (ProgressBar) findViewById(R.id.loader);
//            progressBar.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<UserInfo>> loader) {
//        // Loader reset, so we can clear out our existing data.
//        mAdapter.clear();
//    }
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
                startActivity(new Intent(ListActivity.this, ProfileActivity.class));
                return true;
            case R.id.action_missions:
                startActivity(new Intent(ListActivity.this, MissionsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
