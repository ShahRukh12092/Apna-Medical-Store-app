package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Record extends AppCompatActivity {


    private ArrayList<record_model> arrayList;
    RecyclerView recyclerView;
    String checknumber,number,name,bill,date,method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recyclerView=findViewById(R.id.recyclerview);
        arrayList=new ArrayList<>();
        checknumber=getIntent().getExtras().getString("contact");
        Log.d("order",checknumber);

        setupRecord();

    }

    private void setupAdaptor() {
    Record_Adpater record_adpater= new Record_Adpater(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(record_adpater);
        recyclerView.setHasFixedSize(true);
        record_adpater.notifyDataSetChanged();
    }

    private void setupRecord() {
/*

        arrayList.add(new record_model("12-2-2021","1200","03032242684","Shahrukh","JazzCash"));
        arrayList.add(new record_model("12-2-2021","1200","03032242684","Shahrukh","JazzCash"));
        arrayList.add(new record_model("12-2-2021","1200","03032242684","Shahrukh","JazzCash"));
        arrayList.add(new record_model("12-2-2021","1200","03032242684","Shahrukh","JazzCash"));
        arrayList.add(new record_model("12-2-2021","1200","03032242684","Shahrukh","JazzCash"));
        arrayList.add(new record_model("12-2-2021","1200","03032242684","Shahrukh","JazzCash"));
*/





        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                    Log.d("order", String.valueOf(snapshot1));

                    if(checknumber.equals(snapshot1.child("number").getValue())){
                        Log.d("order", String.valueOf(snapshot1));

                        number= String.valueOf(snapshot1.child("number").getValue());
                        name= String.valueOf(snapshot1.child("name").getValue());
                        bill= String.valueOf(snapshot1.child("bill").getValue());
                        date= String.valueOf(snapshot1.child("date").getValue());
                        method= String.valueOf(snapshot1.child("payment_method").getValue());

                        arrayList.add(new record_model(date,bill,number,name,method));
                        setupAdaptor();
                        Log.d("order", String.valueOf(snapshot1));
                    }

                }


//                Log.i("data", String.valueOf(news));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}