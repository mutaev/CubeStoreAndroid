package com.example.cubestore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class StartActivity extends AppCompatActivity {

    TextView  next_reg, skip;
    EditText edEmail, edPassword;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();


        next_reg = findViewById(R.id.next_reg);
        skip = findViewById(R.id.skip);




        next_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartActivity.this, RegActivity.class);
                startActivity(i);
            }

        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartActivity.this, MainActivity.class);
                startActivity(i);
            }

        });
    }

    public void init(){
        edPassword = findViewById(R.id.editTextTextPersonLogPass);
        edEmail = findViewById(R.id.editTextTextPersonLogEmail);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onClickReg(View view){
        startActivity(new Intent(StartActivity.this,RegActivity.class));
    }
    public void onClickSignIn(View view){
        if(TextUtils.isEmpty(edEmail.getText().toString())){
            Toast.makeText(this,"Email error",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(edPassword.getText().toString())){
            Toast.makeText(this,"Password error",Toast.LENGTH_LONG).show();
        }else{
            mAuth.signInWithEmailAndPassword(edEmail.getText().toString(),edPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            startActivity(new Intent(StartActivity.this,MainActivity.class));
                        }
                    });
        }







    }
}

