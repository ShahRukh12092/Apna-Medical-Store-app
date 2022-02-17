package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity {

    Integer count=0;
    String number,name;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    ImageView order,record,track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        number=getIntent().getExtras().getString("contact");
        name=getIntent().getExtras().getString("name");

        navbr();
        select_options();

    }

    private void select_options() {

        order=findViewById(R.id.order);
        record=findViewById(R.id.record);
        track =findViewById(R.id.trace);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainPage.this,Order.class);
                intent.putExtra("contact",number);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainPage.this,Record.class);
                intent.putExtra("contact",number);
                startActivity(intent);
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainPage.this,Track.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {

        if (count == 0) {
            Toast.makeText(getApplicationContext(), "PRESS Again to EXIT", Toast.LENGTH_LONG).show();
            count +=1;
        } else {
            finishAffinity();
        }
    }

        private void navbr() {
            Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            nav=(NavigationView)findViewById(R.id.navmenu);
            drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

            toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.Home :
                            Toast.makeText(getApplicationContext(),"Home Panel is Open",Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.logout :
                            Toast.makeText(getApplicationContext(),"Logout Panel is Open",Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.profie :
                            Toast.makeText(getApplicationContext(),"Profile Panel is Open",Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;
                    }

                    return true;
                }
            });
        }
    }
