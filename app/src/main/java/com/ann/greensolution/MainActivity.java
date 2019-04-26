package com.ann.greensolution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void organisation(View view) {
        Intent x = new Intent(MainActivity.this, OrganisationActivity.class);
        startActivity(x);
    }

    public void farmer(View view) {
        Intent x = new Intent(this, FarmerActivity.class);
        startActivity(x);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().toString().equalsIgnoreCase("Help"))
        {
            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().toString().equalsIgnoreCase("Logout"))
        {
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle().toString().equalsIgnoreCase("Settings"))
        {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
