package com.example.admin.chillaxguide;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.R.attr.id;
import static android.R.attr.value;

public class MoreInfor extends AppCompatActivity {

    DatabaseHelper myDB;

    int id_to_value = 0;

    TextView txtName, txtCity, txtStreet, txtContact, txtHours, txtSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_infor);

        txtName = (TextView) findViewById(R.id.place_name);
        txtCity = (TextView) findViewById(R.id.place_city);
        txtStreet = (TextView) findViewById(R.id.place_street);
        txtContact = (TextView) findViewById(R.id.place_contact);
        txtHours = (TextView) findViewById(R.id.operating_hours);
        txtSpecial = (TextView) findViewById(R.id.special);

        myDB = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            int value = extras.getInt("id");

            if(value>0){
                Cursor c = myDB.getData(value);
                id_to_value = value;
                c.moveToFirst();

                String placeNam = c.getString(c.getColumnIndex(DatabaseHelper.PLACE_NAME));
                String placeCit = c.getString(c.getColumnIndex(DatabaseHelper.PLACE_CITY));
                String placeStr = c.getString(c.getColumnIndex(DatabaseHelper.PLACE_STREET));
                String contac = c.getString(c.getColumnIndex(DatabaseHelper.CONTACTS));
                String opHours = c.getString(c.getColumnIndex(DatabaseHelper.OPERATION_HOURS));

                if (!c.isClosed()){
                    c.close();
                }

                txtName.setText(placeNam);
                txtCity.setText(placeCit);
                txtStreet.setText(placeStr);
                txtContact.setText(contac);
                txtHours.setText(opHours);
            }
        }
    }
}
