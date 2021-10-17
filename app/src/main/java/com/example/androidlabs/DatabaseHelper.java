package com.example.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "yash.db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseSchema.Messages.NAME + "(" +
                DatabaseSchema.Messages.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseSchema.Messages.Columns.IS_SEND + ", " +
                DatabaseSchema.Messages.Columns.TEXT + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseSchema.Messages.NAME );
        onCreate(db);
    }
}