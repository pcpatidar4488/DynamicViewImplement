package com.example.pc.demoproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pc.demoproject.database.modelClass.ClientDetailsModel;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    //Datebase name
    public static String DATABASE_NAME = "database";
    int DATABASE_VERSION = 1;
    //Table Name
    public static final String TABLE_NAME = "clint_data";
    //Column Name
    private static final String AUTO_INCREMENT_ID = "id";
    public static final String C_NAME = "c_name";
    public static final String C_MOBILE = "c_mobile";
    public static final String C_EMAIL = "c_email";
    public static final String A_NAME = "a_name";
    public static final String A_MOBILE = "a_mobile";
    public static final String C_ADDRESS = "c_address";
    public static final String C_STATUS = "c_status";
    public static final String PRODUCT_NAME = "product_name";
    public static final String REFERENCE_NAME = "reference_name";
    public static final String DATE = "date";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // DBHandler db = new DBHandler(context);
        createTable(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTable(SQLiteDatabase db) {
        if (db == null) {
            db = this.getWritableDatabase();
        }
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + AUTO_INCREMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + C_NAME + " TEXT,"
                + C_MOBILE + " TEXT,"
                + C_EMAIL + " TEXT,"
                + A_NAME + " TEXT,"
                + A_MOBILE + " TEXT,"
                + C_ADDRESS + " TEXT,"
                + C_STATUS + " TEXT,"
                + PRODUCT_NAME + " TEXT,"
                + REFERENCE_NAME + " TEXT,"
                + DATE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void deleteTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable(db);
    }


    public long insertData(ClientDetailsModel model) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_NAME, model.getC_name());
        values.put(C_MOBILE, model.getC_mobile());
        values.put(C_EMAIL, model.getC_email());
        values.put(A_NAME, model.getC_address());
        values.put(A_MOBILE, model.getA_mobile());
        values.put(C_ADDRESS, model.getC_address());
        values.put(C_STATUS, model.getC_status());
        values.put(PRODUCT_NAME, model.getProduct_name());
        values.put(REFERENCE_NAME, model.getReference_name());
        values.put(DATE, model.getDate());
        long result = db.insert(TABLE_NAME, null, values);
        return result;
    }


    public long updateData(ClientDetailsModel model, String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_NAME, model.getC_name());
        values.put(C_MOBILE, model.getC_mobile());
        values.put(C_EMAIL, model.getC_email());
        values.put(A_NAME, model.getC_address());
        values.put(A_MOBILE, model.getA_mobile());
        values.put(C_ADDRESS, model.getC_address());
        values.put(C_STATUS, model.getC_status());
        values.put(PRODUCT_NAME, model.getProduct_name());
        values.put(REFERENCE_NAME, model.getReference_name());
        values.put(DATE, model.getDate());
        long result = db.update(TABLE_NAME, values, AUTO_INCREMENT_ID + " = '" + id + "' ", null);
        return result;
    }

    public long deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, AUTO_INCREMENT_ID + " = '" + id + "'", null);
        db.close();
        return result;
    }


    public ArrayList<ClientDetailsModel> getAllDataList() {
        // Select All Query
        ArrayList<ClientDetailsModel> dataList = new ArrayList<ClientDetailsModel>();
        String selectQuery2 =
                "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery2, null);
        if (cursor.moveToFirst()) {
            do {

                ClientDetailsModel model = new ClientDetailsModel();
                model.setId(cursor.getString(cursor.getColumnIndex(AUTO_INCREMENT_ID)));
                model.setC_name(cursor.getString(cursor.getColumnIndex(C_NAME)));
                model.setC_mobile(cursor.getString(cursor.getColumnIndex(C_MOBILE)));
                model.setC_email(cursor.getString(cursor.getColumnIndex(C_EMAIL)));
                model.setA_name(cursor.getString(cursor.getColumnIndex(A_NAME)));
                model.setA_mobile(cursor.getString(cursor.getColumnIndex(A_MOBILE)));
                model.setC_address(cursor.getString(cursor.getColumnIndex(C_ADDRESS)));
                model.setC_status(cursor.getString(cursor.getColumnIndex(C_STATUS)));
                model.setProduct_name(cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)));
                model.setReference_name(cursor.getString(cursor.getColumnIndex(REFERENCE_NAME)));
                model.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                dataList.add(model);
            } while (cursor.moveToNext());
        }
        return dataList;
    }
}
