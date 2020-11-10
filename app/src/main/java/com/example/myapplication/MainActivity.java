package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnInsertRecordActivity_Click(View view)
    {
this.startActivity(new Intent(this, InsertRecordActivity.class));
    }

    public void btnShowAllRecordActivity_Click(View view)
    {
        this.startActivity(new Intent(this, ShowAllRecordActivity.class));
    }

    public void btnSearchRecordActivity_Click(View view)
    {
        this.startActivity(new Intent(this, SearchRecordActivity.class));

    }



}
