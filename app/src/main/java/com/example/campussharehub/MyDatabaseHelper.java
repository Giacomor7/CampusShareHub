package com.example.campussharehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "EcoExchange.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "product_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "product_name";
    private static final String COLUMN_DESCRIPTION = "product_description";
    private static final String COLUMN_PRICE = "product_price";
    private static final String COLUMN_COLLECTION_INFORMATION = "product_collection_information";
    private static final String COLUMN_IMAGE = "product_image";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE product_list (_id INTEGER PRIMARY KEY AUTOINCREMENT,
        //                              product_name TEXT, product_description TEXT, product_price REAL, product_collection_information TEXT, product_image TEXT);

        String query =
                "CREATE TABLE " +
                        TABLE_NAME +
                        " (" +
                        COLUMN_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME +
                        " TEXT, " +
                        COLUMN_DESCRIPTION +
                        " TEXT, " +
                        COLUMN_PRICE +
                        " TEXT, " +
                        COLUMN_COLLECTION_INFORMATION +
                        " TEXT, " +
                        COLUMN_IMAGE +
                        " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addProduct(String name, String description, String price, String collectionInformation, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_COLLECTION_INFORMATION, collectionInformation);
        cv.put(COLUMN_IMAGE, image);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add product...", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Product added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void wipeData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
