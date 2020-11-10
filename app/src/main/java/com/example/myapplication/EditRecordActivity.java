package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditRecordActivity extends AppCompatActivity
{
    String id="";
    String name="";
    String age="";
    String address="";

    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        db = new DBHandler(this);




        if(getIntent()!=null)
        {
            id = this.getIntent().getStringExtra("ID");


            String selectQuery = "select * from student where id=" + id;
            SQLiteDatabase sdb = db.getReadableDatabase();
            Cursor cursor = sdb.rawQuery(selectQuery, null);

            if(cursor.moveToFirst())
            {
                do{

                    name = cursor.getString(1);
                    age = String.valueOf(cursor.getInt(2));
                    address = cursor.getString(3);

                }while (cursor.moveToNext());
            }
        }

        ((TextView)findViewById(R.id.editTextID)).setText(id);
        ((TextView)findViewById(R.id.editTextName)).setText(name);
        ((TextView)findViewById(R.id.editTextAge)).setText(age);
        ((TextView)findViewById(R.id.editTextAddress)).setText(address);


    }


    public void btnBack_Click(View view)
    {
        Intent intent = new Intent(this, ViewRecordActivity.class);
        intent.putExtra("ID", id);
        this.startActivity(intent);
    }

    public void btnUpdate_Click(View view)
    {
     String msg = "Record updated...";

     SQLiteDatabase sdb = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
        String age = ((EditText)findViewById(R.id.editTextAge)).getText().toString();
        String address = ((EditText)findViewById(R.id.editTextAddress)).getText().toString();

        values.put("name", name);
        values.put("age", age);
        values.put("address", address);


        try
        {
sdb.update("student", values, "id="+id, null);
        }
        catch(SQLiteException ex)
        {
msg = ex.getMessage();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,  ViewRecordActivity.class);
        intent.putExtra("ID",id);
        this.startActivity(intent);

    }
}
