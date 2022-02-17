package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputEditText usercontact, userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button login = findViewById(R.id.login);
        TextView signup = findViewById(R.id.signupClick);
        usercontact = findViewById(R.id.siginContact);
        userpassword = findViewById(R.id.siginPassword);


        String Contact = usercontact.getText().toString().trim();

        Log.i("helo",Contact);
        String Password = String.valueOf(userpassword.getText()).trim();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!validateContact() || !validatepassword()) {
                    return;
                } else {

                    String contact = String.valueOf(usercontact.getText()).trim();
                    String password = String.valueOf(userpassword.getText()).trim();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
                    Query check = reference.orderByChild("number").equalTo(contact);


                    Log.i("num",contact);
                    check.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.i("s", String.valueOf(snapshot));
                            if (snapshot.exists()) {
                                String DBpassword = snapshot.child(contact).child("password").getValue(String.class);
                                String name = snapshot.child(contact).child("name").getValue(String.class);

                                assert DBpassword != null;
                                if (DBpassword.equals(password)) {

                                    Intent intent = new Intent(Login.this, MainPage.class);
                                    intent.putExtra("contact",contact);
                                    intent.putExtra("name",name);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "User Login successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    userpassword.setError("Wrong Password");
                                    userpassword.requestFocus();
                                }
                            } else {
                                usercontact.setError("No User Exist");
                                usercontact.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Log.d("hello", String.valueOf(Contact));
                    Log.d("hello1", String.valueOf(Password));

                }

            }
        });


    }


    private Boolean validatepassword() {

        String password = String.valueOf(userpassword.getText()).trim();
        if (password.isEmpty()) {
            userpassword.setError("Password should not be empty");
            userpassword.requestFocus();
            return false;
        } else {
            userpassword.setError(null);
            return true;
        }
    }

    private Boolean validateContact() {


        String contact = String.valueOf(usercontact.getText()).trim();
        Log.i("12",contact);
        if (contact.isEmpty()) {
            usercontact.setError("Contact should not be empty");
            usercontact.requestFocus();
            return false;
        } else if (contact.length() != 11) {
            usercontact.setError(" Contact contain only 11 digits");
            usercontact.requestFocus();
            return false;
        } else {
            usercontact.setError(null);
            return true;
        }
    }
}