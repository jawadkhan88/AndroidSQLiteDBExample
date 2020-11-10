package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewRecordActivity extends AppCompatActivity
{
    String id="";
    String name="";
    String age="";
    String address="";

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        db = new DBHandler(this);




        if(getIntent()!=null)
        {
            id = this.getIntent().getStringExtra("ID");


            String selectQuery = "select * from student where id = " + id;
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


    public void btnEdit_Click(View view)
    {
        Intent intent = new Intent(this, EditRecordActivity.class);
        intent.putExtra("ID", id);
        this.startActivity(intent);

    }

    public void btnDelete_Click(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete the record?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String msg  = "Record deleted...";
                SQLiteDatabase sdb = db.getWritableDatabase();
                try
                {
                    sdb.delete("student","id="+id, null);
                }
                catch(SQLException ex)
                {
                    msg = ex.getMessage();
                    Toast.makeText(ViewRecordActivity.this, msg, Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(ViewRecordActivity.this, ShowAllRecordActivity.class);
                ViewRecordActivity.this.startActivity(intent);

            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void btnBack_Click(View view)
    {
        this.startActivity(new Intent(this, ShowAllRecordActivity.class));
    }
}
