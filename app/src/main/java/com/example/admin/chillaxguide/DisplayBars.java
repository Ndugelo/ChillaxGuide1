package com.example.admin.chillaxguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.R.attr.bitmap;
import static android.R.attr.contextClickable;
import static android.R.attr.contextUri;
import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.os.Build.VERSION_CODES.M;
import static com.facebook.FacebookSdk.getApplicationContext;
import static java.security.AccessController.getContext;

/**
 * Created by admin on 2017/02/27.
 */

public class DisplayBars extends AppCompatActivity {
    private RecyclerView recyclerList;
    private DatabaseReference mDatabaseReference;
    PlaceDetails details;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_list);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("places");
        recyclerList = (RecyclerView) findViewById(R.id.recycler_list);
        recyclerList.setHasFixedSize(true);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PlaceDetails, BarsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PlaceDetails, BarsViewHolder>(
                PlaceDetails.class,
                R.layout.activity_list_item,
                BarsViewHolder.class,
                mDatabaseReference
        ) {
            @Override
            protected void populateViewHolder(BarsViewHolder viewHolder, PlaceDetails model, final int position) {

                final String bar_key = getRef(position).getKey();

                details = model;
                viewHolder.setPlaceName(model.getPlaceName());
                viewHolder.setPlaceCity(model.getPlaceCity());
                viewHolder.setPlaceStreet(model.getPlaceStreet());
                //viewHolder.setPlaceContact(model.getPlaceContact());
                viewHolder.setHours(model.getHours());

                viewHolder.setPhotoUrl(getApplicationContext(), model.getPhotoUrl());
               // viewHolder.setBitmap(getApplicationContext(), model.getBitmap());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent nextIntent= new Intent(getApplicationContext(), MoreInfor.class);
                        nextIntent.putExtra("bar_key", bar_key);
                        startActivity(nextIntent);

                        //Toast.makeText(getApplicationContext(), "Bar Key: " + bar_key, Toast.LENGTH_SHORT).show();
                        //System.out.println("Bar Key: " + bar_key);

                    }
                });

            }
        };

       recyclerList.setAdapter(firebaseRecyclerAdapter);
    }

//    public  void moreDeta(View view){
////        startActivity(new Intent(this, MoreInfor.class));
//    }

    public static  class BarsViewHolder extends  RecyclerView.ViewHolder {
        View mView;

        public BarsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setPlaceName(String name) {
            TextView tvName = (TextView) mView.findViewById(R.id.placeName);
            tvName.setText(name);
        }

        public void setPlaceCity(String placeCity) {
            TextView tvPlace = (TextView) mView.findViewById(R.id.city_text_view);
            tvPlace.setText(placeCity);
        }

        public void setPlaceStreet(String placeStreet) {
            TextView tvPlaceStreet = (TextView) mView.findViewById(R.id.address_text_view);
            tvPlaceStreet.setText(placeStreet);
        }

        public void setPlaceContact(String placeContact) {
            TextView tvPlaceContact = (TextView) mView.findViewById(R.id.txtPlace_contact);
           tvPlaceContact.setText(placeContact);
        }

        public void setHours(String placeHours) {
            TextView tvPlaceHours = (TextView) mView.findViewById(R.id.hours_text_view);
            tvPlaceHours.setText(placeHours);
        }

        public void setPhotoUrl(Context context, String photoUrl) {
            ImageView imgView = (ImageView) mView.findViewById(R.id.imgv_logo);
            Picasso.with(context).load(photoUrl).into(imgView);
        }

//        public void setBitmap(Context context, Bitmap bitmap) {
//            ImageView imgView = (ImageView) mView.findViewById(R.id.imgv_logo);
//            Picasso.with(context).load(String.valueOf(bitmap)).into(imgView);
//        }

    }
}
