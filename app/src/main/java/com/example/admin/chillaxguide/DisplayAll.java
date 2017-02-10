package com.example.admin.chillaxguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class DisplayAll extends AppCompatActivity {
    DatabaseHelper myDB;
    private ListView obj;
    private ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        myDB = new DatabaseHelper(this);
        arrayList = myDB.getAllData();
        if(!arrayList.isEmpty()) {
            //problem
            WordAdapter arrayAdapter = new WordAdapter(this, arrayList);
            //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

            obj = (ListView)findViewById(R.id.list_view1);
            obj.setAdapter(arrayAdapter);
            obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id_To_Search = position + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Toast.makeText(DisplayAll.this, " selected", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MoreInfor.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }}


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.item1:Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),MoreInfor.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent (this,MainActivity.class);
        startActivity(i);
        return;
    }
}
