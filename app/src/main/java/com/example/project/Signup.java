package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    Button login;
    TextView t;
    TextInputEditText username, userpassword, usercontact;
    FirebaseDatabase root;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        login = findViewById(R.id.signupubutton);
        username = findViewById(R.id.Signupusername);
        userpassword = findViewById(R.id.SignupuserPassword);
        usercontact = findViewById(R.id.signupuserContact);
        t=findViewById(R.id.haveaccount);




        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = String.valueOf(username.getText()).trim();
                String password = String.valueOf(userpassword.getText()).trim();
                String contact = String.valueOf(usercontact.getText()).trim();
                HelperSignup helperSignup=new HelperSignup(name,password,contact);

                Log.d("hello", String.valueOf(name));
                Log.d("hello1", String.valueOf(password));
                Log.d("hello2", String.valueOf(contact));

                if(!validateUser() || !validatepassword() ||!validateContact()){
                    return;
                }else {

                    root = FirebaseDatabase.getInstance();
                    reference=root.getReference("user");
                    Query check = reference.orderByChild("number").equalTo(contact);

                    check.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){

                                username.setError("User Already Exist");
                                username.requestFocus();
                            }else {

                                reference.child(contact).setValue(helperSignup);

                                Intent intent = new Intent(Signup.this, MainPage.class);
                                intent.putExtra("contact",contact);
                                intent.putExtra("name",name);

                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "User Signed Up successfully", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

            }
        });

    }

    private Boolean validateUser() {

        String name = String.valueOf(username.getText()).trim();
        if (name.isEmpty()) {
            username.setError("User name should not be empty");
            username.requestFocus();
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private Boolean validatepassword() {

        String password = String.valueOf(userpassword.getText()).trim();
        if (password.isEmpty()) {
            userpassword.setError("Password should not be empty");
            userpassword.requestFocus();
            return false;
        } else if (password.length() < 8) {
            userpassword.setError("Password should contain More than 8 digits");
            userpassword.requestFocus();
            return false;
        } else {
            userpassword.setError(null);
            return true;
        }
    }

    private Boolean validateContact() {


        String contact = String.valueOf(usercontact.getText()).trim();
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





