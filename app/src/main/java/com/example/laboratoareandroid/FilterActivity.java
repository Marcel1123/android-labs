package com.example.laboratoareandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id1:
                goHome();
                return true;
            case R.id.id2:
                goOptiune2();
                return true;
            case R.id.id3:
                goOptiune3();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goOptiune2() {
        Intent intent = new Intent(this, Optiune2Activity.class);
        startActivity(intent);
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goOptiune3() {
        Intent intent = new Intent(this, Optiune3Activity.class);
        startActivity(intent);
    }
}
