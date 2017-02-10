package com.example.admin.chillaxguide;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.listViewWhiteStyle;
import static android.R.attr.resource;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by admin on 2017/01/19.
 */

public class WordAdapter extends BaseAdapter{

    //DatabaseHelper myDB;
    private Context context;
    private ArrayList<String> details;

    public WordAdapter(Context context, ArrayList<String> details) {
        this.context = context;
        this.details = details;
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public String getItem(int position) {
        return details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                    R.layout.activity_list_item, parent, false);
        }
        //End of inflate method

        //getting our object
        String object = getItem(position);

        //Splitting our object in to the necessary data
        String [] arrayOfString = object.split(" ");


        try{
            TextView editTextPlaceName = (TextView) listItemView.findViewById(R.id.placeName);
            editTextPlaceName.setText(arrayOfString[0]);

            TextView editTextCity = (TextView) listItemView.findViewById(R.id.city_text_view);
            editTextCity.setText(arrayOfString[1]);

            TextView editTextStreet = (TextView) listItemView.findViewById(R.id.address_text_view);
            editTextStreet.setText(arrayOfString[2]);

            TextView editTextContacts = (TextView) listItemView.findViewById(R.id.contacts_text_view);
            editTextContacts.setText(arrayOfString[3]);

            TextView editTextHours = (TextView) listItemView.findViewById(R.id.hours_text_view);
            editTextHours.setText(arrayOfString[4]);


        }catch (Exception epe)
        {
            epe.printStackTrace();

        }

        //ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        return listItemView;
    }
}
