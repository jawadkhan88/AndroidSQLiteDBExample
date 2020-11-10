package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertRecordActivity extends AppCompatActivity
{
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);

        db = new DBHandler(this);
    }

    public void btnSave_Click(View view)
    {
        SQLiteDatabase sdb = db.getWritableDatabase();
        //ContentValues are used to insert new rows into tables. Each Content Values object represents a single table row as a map of column names to values.
        ContentValues values = new ContentValues();

        String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
        String age = ((EditText)findViewById(R.id.editTextAge)).getText().toString();
        String address = ((EditText)findViewById(R.id.editTextAddress)).getText().toString();

        values.put("name", name);
        values.put("age", age);
        values.put("address", address);

        try
        {
          sdb.insertOrThrow("student", null, values);
            Toast.makeText(this, "Recorded inserted...", Toast.LENGTH_SHORT).show();
        }
        catch(SQLiteException ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void btnReset_Click(View view)
    {
        this.startActivity(new Intent(this, InsertRecordActivity.class));
    }

    public void btnBack_Click(View view)
    {
        this.startActivity(new Intent(this, MainActivity.class));
    }
}
