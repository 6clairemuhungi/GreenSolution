package com.ann.greensolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FarmerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
    }

    public void login(View view) {
        Intent x=new Intent(this,DisplayActivity.class);
        startActivity(x);
    }

    public void signup(View view) {
        Intent x=new Intent(this,DisplayActivity.class);
        startActivity(x);
    }
}
