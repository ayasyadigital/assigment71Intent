package com.niketgoel.assigment71intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bundle extras = getIntent().getExtras();
        String user = extras.getString("username");
        TextView tv = (TextView) findViewById(R.id.TextView01);
        tv.setText("Welcome    :  " + user);


    }

}
