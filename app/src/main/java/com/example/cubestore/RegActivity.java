package com.example.cubestore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegActivity extends AppCompatActivity {
    EditText edEmail,edPassword;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        init();
    }
    public void init(){
        edPassword = findViewById(R.id.editTextTextPersonLogPass);
        edEmail = findViewById(R.id.editTextTextPersonRegEmail);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onClickSingInActivity(View view){
        startActivity(new Intent(RegActivity.this,StartActivity.class));
    }
    public void onClickRegister(View view){
        if(TextUtils.isEmpty(edEmail.getText().toString())){
            Toast.makeText(this,"Email error",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(edPassword.getText().toString())){
            Toast.makeText(this,"Password error",Toast.LENGTH_LONG).show();
        }else{
            mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(),edPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            startActivity(new Intent(RegActivity.this,StartActivity.class));
                        }
                    });
        }
    }

}