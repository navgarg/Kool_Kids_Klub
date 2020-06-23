package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {

    String user_name;
    private FirebaseListAdapter<ChatInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email =user.getEmail();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("User");
        usersRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    user_name = datas.child("name").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // User not signed in
            Toast.makeText(ChatActivity.this, "Kindly sign in", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ChatActivity.this, LoginActivity.class));
        } else {
            // User signed in.
            displayChat();
        }

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText type_message = (EditText)findViewById(R.id.type_message);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("Chat")
                        .push()
                        .setValue(new ChatInfo(type_message.getText().toString(), user_name));

                // Clear the input
                type_message.setText("");
            }
        });
    }



            private void displayChat() {
                ListView list_view = (ListView)findViewById(R.id.message_list);

                adapter = new FirebaseListAdapter<ChatInfo>(this, ChatInfo.class,
                        R.layout.message_item, FirebaseDatabase.getInstance().getReference().child("Chat").orderByChild("messageTime")) {
                    @Override
                    protected void populateView(View v, ChatInfo chat, int position) {
                        TextView messageText = (TextView)v.findViewById(R.id.message_text);
                        TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                        TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                        messageText.setText(chat.getMessageText());
                        messageUser.setText(chat.getUser_name());

                        messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                                chat.getMessageTime()));
                    }
                };

                list_view.setAdapter(adapter);

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
            startActivity(new Intent(ChatActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}