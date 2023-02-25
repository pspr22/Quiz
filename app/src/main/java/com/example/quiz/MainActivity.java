package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<modelClass> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));



        modelClass ModelClass1 = new modelClass("history","History");
        modelClass ModelClass2 = new modelClass("sports","Sports");
        modelClass ModelClass3 = new modelClass("films","Films");
        modelClass ModelClass4 = new modelClass("olympics","Olympics");
        modelClass ModelClass5 = new modelClass("inventors","Inventors");
        modelClass ModelClass6 = new modelClass("countries","Countries");
        modelClass ModelClass7 = new modelClass("gods","Gods");


        arrayList.add(ModelClass1);
        arrayList.add(ModelClass2);
        arrayList.add(ModelClass3);
        arrayList.add(ModelClass4);
        arrayList.add(ModelClass5);
        arrayList.add(ModelClass6);
        arrayList.add(ModelClass7);

        adapter = new RecyclerAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuSignOut:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}