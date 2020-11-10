package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_record);
    }

    public void btnBack_Click(View view)
    {
        this.startActivity(new Intent(this, MainActivity.class));
    }

    public void btnSearch_Click(View view)
    {
        String id = ((EditText)findViewById(R.id.editTextID)).getText().toString();
        String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
        String age = ((EditText)findViewById(R.id.editTextAge)).getText().toString();
        String address = ((EditText)findViewById(R.id.editTextAddress)).getText().toString();

        ArrayList wherepart = new ArrayList();

        if(id.trim().length()!=0)
        {
            wherepart.add("id = " + id);
        }

        if(name.trim().length()!=0)
        {
            wherepart.add("name like '%" + name + "%'");
        }

        if(age.trim().length()!=0)
        {
            wherepart.add("age = " + age);
        }

        if(address.trim().length()!=0)
        {
            wherepart.add("address like '%" + address + "%'");
        }

        String wherepartstring = "";

        int i = 0;
        for(i=0; i<wherepart.size()-1; i++)
        {
            wherepartstring = wherepartstring + " " + wherepart.get(i) + " and ";
        }

        wherepartstring = wherepartstring + wherepart.get(i);

        String selectQuery = "select * from student where " + wherepartstring;

        //Toast.makeText(this, selectQuery, Toast.LENGTH_LONG).show();

      Intent intent = new Intent(this, ShowAllRecordActivity.class);
      intent.putExtra("QUERY", selectQuery);
      this.startActivity(intent);
    }
}
