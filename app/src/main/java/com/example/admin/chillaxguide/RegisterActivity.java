package com.example.admin.chillaxguide;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.os.Build.VERSION_CODES.M;
import static com.example.admin.chillaxguide.R.id.city;
import static com.example.admin.chillaxguide.R.id.contacts;
import static com.example.admin.chillaxguide.R.id.placeName;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText editTextEmail, editTextPassword, editTextPlaceName, editTextCity, editTextStreet, editTextContact, editTextHours;

    Button btnRegister;
    ImageView imLogo;
   // private  static final int NO_IMAGE_PROVIDED = -1;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDetailsDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDetailsDatabaseReference = mFirebaseDatabase.getReference().child("details");

        myDB = new DatabaseHelper(this);

        editTextEmail = (EditText)findViewById(R.id.email);
        editTextPassword = (EditText)findViewById(R.id.password);
        editTextPlaceName = (EditText)findViewById(R.id.placeName);
        editTextCity = (EditText)findViewById(city);
        editTextStreet = (EditText)findViewById(R.id.street);
        editTextContact = (EditText)findViewById(contacts);
        editTextHours = (EditText)findViewById(R.id.hours);

        btnRegister = (Button)findViewById(R.id.register);
       imLogo = (ImageView)findViewById(R.id.logo);
    }


    public EditText getPlaceName() {
        return editTextPlaceName;
    }

    public EditText getCity() {
        return editTextCity;
    }

    public EditText getStreet() {
        return editTextStreet;
    }
    public EditText getContact() {
        return editTextContact;
    }
    public EditText getHours() {
        return editTextHours;
    }

    public void reg(View view) {
       boolean isInserted =  myDB.insertData(editTextEmail.getText().toString(), editTextPassword.getText().toString(),
                editTextPlaceName.getText().toString(), editTextCity.getText().toString(),
                editTextStreet.getText().toString(),
                editTextContact.getText().toString(), editTextHours.getText().toString());

        if(isInserted == true)

            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

        else
        {
            Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getApplicationContext(), DisplayAll.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent (this,GoogleSignInActivity.class);
        startActivity(i);
        return;
    }
}
