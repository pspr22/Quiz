package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Result extends AppCompatActivity {
    
    TextView hc,hb,sc,sb,fc,fb,oc,ob,ic,ib,cc,cb;
    
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        hc = findViewById(R.id.hc);
        hb = findViewById(R.id.hb);
        sc = findViewById(R.id.sc);
        sb = findViewById(R.id.sb);
        fc = findViewById(R.id.fc);
        fb = findViewById(R.id.fb);
        oc = findViewById(R.id.oc);
        ob = findViewById(R.id.ob);
        ic = findViewById(R.id.ic);
        ib = findViewById(R.id.ib);
        cc = findViewById(R.id.cc);
        cb = findViewById(R.id.cb);
        
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hc.setText(snapshot.child("History").child("Score").child("CurrentScore").getValue().toString());
                hb.setText(snapshot.child("History").child("Score").child("BestScore").getValue().toString());
                sc.setText(snapshot.child("Sports").child("Score").child("CurrentScore").getValue().toString());
                sb.setText(snapshot.child("Sports").child("Score").child("BestScore").getValue().toString());
                oc.setText(snapshot.child("Olympics").child("Score").child("CurrentScore").getValue().toString());
                ob.setText(snapshot.child("Olympics").child("Score").child("BestScore").getValue().toString());
                ic.setText(snapshot.child("Inventors").child("Score").child("CurrentScore").getValue().toString());
                ib.setText(snapshot.child("Inventors").child("Score").child("BestScore").getValue().toString());
                cc.setText(snapshot.child("Countries").child("Score").child("CurrentScore").getValue().toString());
                cb.setText(snapshot.child("Countries").child("Score").child("BestScore").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Result.this, "Error Occured", Toast.LENGTH_SHORT).show();

            }
        });



    }
}