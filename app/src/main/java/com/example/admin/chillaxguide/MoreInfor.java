package com.example.admin.chillaxguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.os.Build.VERSION_CODES.M;

public class MoreInfor extends AppCompatActivity {

    PlaceDetails placeDetails;
    WordAdapter wordAdapter;
    RegisterActivity registerActivity;
    private Button btnLocation;
    private String placeStreetGlobal;
    private String placeCityGlobal;

    ImageView imageView;
    TextView txtName, txtCity, txtStreet, txtContact, txtHours, txtSpecial;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_infor);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("places");


        String barKey = getIntent().getStringExtra("bar_key");
        Log.v("MORE INFOR", " Bar Key : " + barKey);

        txtName = (TextView) findViewById(R.id.txtPlace_name);
        txtCity = (TextView) findViewById(R.id.txtPlace_city);
        txtStreet = (TextView) findViewById(R.id.txtPlace_street);
        txtContact = (TextView) findViewById(R.id.txtPlace_contact);
        txtHours = (TextView) findViewById(R.id.txtPlace_time);
        txtSpecial = (TextView) findViewById(R.id.txtPlace_special);
        imageView = (ImageView) findViewById(R.id.proPic);
        btnLocation = (Button) findViewById(R.id.btnDirection);

        mDatabaseReference.child(barKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String placename = (String) dataSnapshot.child("placeName").getValue();
                final String placeCity = (String) dataSnapshot.child("placeCity").getValue();
                String placeStreet = (String) dataSnapshot.child("placeStreet").getValue();
                String placeContact = (String) dataSnapshot.child("placeContact").getValue();
                String placeHours = (String) dataSnapshot.child("hours").getValue();
                String placeSpecial = (String) dataSnapshot.child("special").getValue();
                String photoUrl = (String) dataSnapshot.child("photoUrl").getValue();


                txtName.setText("Place Name :" + placename);
                txtCity.setText("City / Town :" +placeCity);
                txtStreet.setText("Street :" +placeStreet);
                txtContact.setText("Contacts :" +placeContact);
                txtHours.setText("Operation Time :" +placeHours);
                txtSpecial.setText("Special / Promotion :" +placeSpecial);
                Picasso.with(getApplicationContext()).load(photoUrl).into(imageView);


                placeStreetGlobal = placeStreet;
                placeCityGlobal = placeCity;
                btnLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passLocation(placeStreetGlobal,placeCityGlobal);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void passLocation(String placeStreet,String placeCity) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.navigation:q= "+placeCity+", "+placeStreet));
        startActivity(intent);
    }

//    public void Direction(View view){
//            startActivity(new Intent(MoreInfor.this, MapsActivity.class));
//    }
}
