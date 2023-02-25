package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText etSinUpE,etSignUpP;
    Button btnSignUp;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSinUpE = findViewById(R.id.etSUEmail);
        etSignUpP = findViewById(R.id.etSUPass);
        btnSignUp = findViewById(R.id.signUp2);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etSinUpE.getText().toString();
                String Password = etSignUpP.getText().toString();
                signUp(userName,Password);
                btnSignUp.setClickable(false);
            }
        });


    }

    public void signUp(String userName,String Password){

        auth.createUserWithEmailAndPassword(userName,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "You are sucessfully Signed Up!!!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(SignUp.this, "Problem Occured in signing Up!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}