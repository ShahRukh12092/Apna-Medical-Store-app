package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order extends AppCompatActivity {


    TextView notInterested;
    Button btn;
    Spinner spinner, spinner1,spinner2;
    Integer x, x1, x2,x3;
    String m1,names,number;
    EditText Q1,Q2,Q3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        names=getIntent().getExtras().getString("name");
        number=getIntent().getExtras().getString("contact");

        spinner = findViewById(R.id.spinner2);
        spinner1 = findViewById(R.id.spinner3);
        spinner2= findViewById(R.id.spinner4);
        Q1=findViewById(R.id.q1);
        Q2=findViewById(R.id.q2);
        Q3=findViewById(R.id.q3);

        ArrayList<String> price = new ArrayList<>();
        ArrayList<String> news = new ArrayList<>();
        news.add("Select the Medicine");
        price.add("Select the Price");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("medicines");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    news.add(String.valueOf(snapshot1.child("name").getValue()));
                    price.add(String.valueOf(snapshot1.child("price").getValue()));
                }

                Log.i("data", String.valueOf(news));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, news);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String w=Q1.getText().toString();
                Log.i("q",w);
                if (i == 0) {
  //                  t.setText(" ");
                    x1=0;
                } else {
//                    t.setText(news.get(i));
                    m1=news.get(i);
                    x1 = Integer.valueOf(price.get(i));
                  //  news.remove(news.get(i));
//                    p1=price.get(i);
                  //  price.remove(price.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                  //t1.setText(" ");
                    x=0;
                } else {

    //                t1.setText(news.get(i));
                    x = Integer.valueOf(price.get(i));
                   // news.remove(news.get(i));
                   // price.remove(price.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    //t1.setText(" ");
                    x2=0;
                } else {

                    // t1.setText(news.get(i));
                    x2 = Integer.valueOf(price.get(i));
                    // news.remove(news.get(i));
                    // price.remove(price.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btn= findViewById(R.id.b);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q1= Q1.getText().toString();
                String q2= Q2.getText().toString();
                String q3= Q3.getText().toString();

                if(q1.isEmpty()){
                    q1= String.valueOf(1);
                }
                if(q2.isEmpty()){
                    q2= String.valueOf(1);
                }
                if(q3.isEmpty()){
                    q3= String.valueOf(1);
                }
                Integer q11=Integer.valueOf(q1);
                Integer q12=Integer.valueOf(q2);
                Integer q13=Integer.valueOf(q3);

                x3 = x*q11 + x1*q12 +x2*q13;


                String s = String.valueOf(x3);

                if(x3!=0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String date = sdf.format(new Date());
                    Log.d("date", date);



                    Intent intent = new Intent(Order.this, Order_continue.class);
                    intent.putExtra("Bill", s);
                    intent.putExtra("Date", date);
                    intent.putExtra("Name", names);
                    intent.putExtra("Contact", getIntent().getExtras().getString("contact"));
                    Log.i("bill", String.valueOf(x3));
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "No order placed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Order.this, MainPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });




/*



        notInterested=findViewById(R.id.notInterested);
        btn=findViewById(R.id.orderBtn);


        notInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Order.this,MainPage.class);
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Order.this,Order_continue.class);
                startActivity(intent);
                finish();
            }
        });
*/




    }

}


