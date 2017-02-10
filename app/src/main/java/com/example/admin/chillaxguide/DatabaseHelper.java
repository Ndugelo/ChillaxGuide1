package com.example.admin.chillaxguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.name;
import static android.R.attr.version;
import static com.example.admin.chillaxguide.R.id.city;
import static com.example.admin.chillaxguide.R.id.contacts;
import static com.example.admin.chillaxguide.R.id.email;
import static com.example.admin.chillaxguide.R.id.hours;
import static com.example.admin.chillaxguide.R.id.street;

/**
 * Created by admin on 2017/01/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "chill_guide.db";
    public static final String TABLE_NAME = "chill_guide";

    public static final String ID = "id";
    public static final String OWNER_EMAIL = "owner_email";
    public static final String OWNER_PASSWORD = "owner_password";
    public static final String PLACE_NAME = "place_name";
    public static final String PLACE_CITY = "city";
    public static final String PLACE_STREET = "street";
    public static final String CONTACTS = "contacts";
    public static final String OPERATION_HOURS = "operation_hours";


    public DatabaseHelper(Context context) {super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
        "(id integer primary key, owner_email text, owner_password text, place_name text, city text, street text, contacts text, operation_hours text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String email, String password, String name, String city, String street, String contacts, String hours){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("owner_email", email);
        cv.put("owner_password", password);
        cv.put("place_name", name);
        cv.put("city", city);
        cv.put("street", street);
        cv.put("contacts", contacts);
        cv.put("operation_hours", hours);

        db.insert(TABLE_NAME, null, cv);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + ID +" = "+id+"", null);
        return c;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateData(Integer id, String email, String password, String name, String city, String street, String contacts, String hours){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("owner_email", email);
        cv.put("owner_password", password);
        cv.put("place_name", name);
        cv.put("city", city);
        cv.put("street", street);
        cv.put("contacts", contacts);
        cv.put("operation_hours", hours);

        db.update(TABLE_NAME, cv, "id = ? ", new String[]{Integer.toString(id)});
        return  true;
    }

    public Integer deleteData(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ? ", new String[] {Integer.toString(id)});
    }

    public ArrayList<String> getAllData(){
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME +"", null);
        c.moveToFirst();

        while (c.isAfterLast() == false){
            arrayList.add(c.getString(c.getColumnIndex(PLACE_NAME)) +" "+c.getString(c.getColumnIndex(PLACE_CITY))
                    +" "+c.getString(c.getColumnIndex(PLACE_STREET))+" "+c.getString(c.getColumnIndex(CONTACTS))
                    +" "+c.getString(c.getColumnIndex(OPERATION_HOURS)));
            c.moveToNext();
        }

        return arrayList;
    }
}
