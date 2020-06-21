package com.test.koolkidsklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView signup = findViewById(R.id.sign_up_user);

        final EditText name = findViewById(R.id.enter_name);
        final EditText email = findViewById(R.id.enter_email);
        final EditText lat = findViewById(R.id.enter_lat);
        final EditText longitude = findViewById(R.id.enter_long);
        final EditText phone = findViewById(R.id.enter_phone);
        final EditText pass = findViewById(R.id.enter_pass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().equals("")
                        && !email.getText().toString().equals("")
                        && !lat.getText().toString().equals("")
                        && !longitude.getText().toString().equals("")
                        && !phone.getText().toString().equals("")
                        & !pass.getText().toString().equals(""))
                    {
                    final FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseDatabase.getInstance()
                                                .getReference()
                                                .child("Users")
                                                .push()
                                                .setValue(new UserInfo(name.getText().toString(), email.getText().toString(),
                                                        lat.getText().toString(), longitude.getText().toString(), phone.getText().toString()));
                                        Toast.makeText(SignupActivity.this, "Signed up successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SignupActivity.this, MapsActivity.class));
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }

                else{
                    Toast.makeText(SignupActivity.this, "Fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}