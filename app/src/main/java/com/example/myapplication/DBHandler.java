package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper
{
    public DBHandler(Context context)
    {
        super(context, "University", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableQuery = "CREATE TABLE student("+
                                  "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                  "name TEXT,"+
                                  "age INTEGER,"+
                                  "address TEXT)";
        try
        {
            db.execSQL(createTableQuery);
        }
        catch(SQLiteException ex)
        {
            Log.i("DB_ERROR", ex.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
db.execSQL("DROP TABLE IF EXISTS student");
this.onCreate(db);
    }
}
