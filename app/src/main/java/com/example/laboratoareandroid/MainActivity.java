package com.example.laboratoareandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listaIteme;
    TextView textDisponibil;
    TextView textView;
    String[] ListElements = new String[] {
            "Android",
            "PHP",
            "Python",
            "Java",
            "JavaScript",
            "C",
            "C++",
            "C#"
    };

    String[] DescriereListElements = new String[] {
            "Descriere: Android",
            "Descriere: PHP",
            "Descriere: Python",
            "Descriere: Java",
            "Descriere: JavaScript",
            "Descriere: C",
            "Descriere: C++",
            "Descriere: C#"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaIteme = findViewById(R.id.listaIteme);
        textDisponibil = findViewById(R.id.textDisponibil);
        textView = findViewById(R.id.textView4);
        final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (MainActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listaIteme.setAdapter(adapter);

        listaIteme.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = ((TextView)view).getText().toString();
                        textDisponibil.setText(DescriereListElements[i]);
                    }
                }
        );

        if(savedInstanceState != null){
            textDisponibil.setText(savedInstanceState.getCharSequence("Text"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("Text", textDisponibil.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meniu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id1:
                return true;
            case R.id.id2:
                openCartMenu();
                return true;
            case R.id.id3:
                openLoginMenu();
                return true;
            case R.id.id4:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openCartMenu() {
        Intent intent = new Intent(this, Optiune2Activity.class);
        startActivity(intent);
    }

    public void openLoginMenu() {
        Intent intent = new Intent(this, Optiune3Activity.class);
        startActivity(intent);
    }

    public void openSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openSensorsSceen(View view){
        startActivity(new Intent(this, SensorActivity.class));
    }

    public void goToCamera(View view){
        startActivity(new Intent(this, CameraActivity.class));
    }

    public void saveContent(View view){

        Context context = getApplicationContext();
        String filename = "myfile.txt";

        File file = new File(context.getFilesDir(), filename);
        String fileContents = (String) textDisponibil.getText();

        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();
            textView.setText("Text salvat: " + new String(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
