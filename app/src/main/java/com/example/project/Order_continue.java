package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;


public class Order_continue<Public> extends AppCompatActivity {


    Spinner spinner;
    TextView selected,number,bill;
    String contacts,date,bills,names;
    Button btn1,btn2,btn3,btn4;
    ImageView imageView;
    String methods[]={"Select Payment Method","EasyPaisa","JazzCash","UPaisa"};
    Uri uri;
    String method;
    String uri_image;
    StorageReference storageReference= FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_continue);
        Settingspinner();

        contacts=getIntent().getExtras().getString("Contact");
        date=getIntent().getExtras().getString("Date");
        bills= getIntent().getExtras().getString("Bill");
        names= getIntent().getExtras().getString("Name");



        PickingImage();



    }


    private void PickingImage() {

        imageView=findViewById(R.id.imagepicker);
        bill=findViewById(R.id.BILL);
        btn1=findViewById(R.id.pickImage);
        btn2=findViewById(R.id.replaceImage);
        btn3=findViewById(R.id.done);
        btn4=findViewById(R.id.complete);

         bill.setText("Your Bill is "+bills+" RS");
        bill.setTextSize(30);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImagePicker.Companion.with(Order_continue.this)
                      .crop().start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImagePicker.Companion.with(Order_continue.this)
                        .crop().start();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.VISIBLE);

                if(uri != null){

                    uploadimageFirbase(uri);
                }else {
                    Toast.makeText(getApplicationContext(), "plz select the image", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("name",names);


                FirebaseDatabase root = FirebaseDatabase.getInstance();
                DatabaseReference reference = root.getReference("orders");
                String id = reference.push().getKey();


                Horder horder = new Horder(id,contacts,names,date,bills,method,uri.toString());

                reference.child(id).setValue(horder);


                Intent intent=new Intent(Order_continue.this,MainPage.class);
                startActivity(intent);
                finish(); }
        });



    }



    private String getFileextension(Uri muri) {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(muri));
    }

    private void uploadimageFirbase(Uri uri) {

        StorageReference storageReference1=storageReference.child(System.currentTimeMillis()+"."+getFileextension(uri));

        storageReference1.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uri_image=uri.toString();
                        Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(getApplicationContext(), "IN progress", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Not able to upload image", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         uri= data.getData();
        imageView.setImageURI(uri);

        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);

            }

    private void Settingspinner() {

        spinner=findViewById(R.id.spinner);
        selected=findViewById(R.id.hello);
        number=findViewById(R.id.number);

        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,methods);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    selected.setText(" ");
                    number.setText(" ");
                }else {

                    method=methods[i];
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("info");
                    Query check = reference.orderByChild("method").equalTo(methods[i]);

                    check.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.i("info", String.valueOf(snapshot));
                            String name = snapshot.child(methods[i]).child("name").getValue(String.class);
                            String number1 = snapshot.child(methods[i]).child("number").getValue(String.class);
                            selected.setText(name);
                            selected.setTextSize(20);
                            number.setText(number1);
                            number.setTextSize(20);
                            imageView=findViewById(R.id.imagepicker);
                            btn1=findViewById(R.id.pickImage);
                            imageView.setVisibility(View.VISIBLE);
                            btn1.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}




/*
                ImagePicker.Companion.with(Order_continue.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
*/
/*                          .cropOval()	    		//Allow dimmed layer to have a circle inside
                        .cropFreeStyle()	    //Let the user to resize crop bounds
                        .galleryOnly()          //We have to define what image provider we want to use
                        .maxResultSize(1080, 1080)*//*
	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
*/
