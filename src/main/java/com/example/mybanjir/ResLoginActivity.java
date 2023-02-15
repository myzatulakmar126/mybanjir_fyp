package com.example.mybanjir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResLoginActivity extends AppCompatActivity {

    TextView create_acc;
    EditText txtEmailRes, txtPswdRes;
    Button btnSignIn;

    String resEmail, resPswd;
    private FirebaseAuth mAuth;
    rescuer res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reslogin);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        txtEmailRes = findViewById(R.id.txtEmailRes);
        txtPswdRes = findViewById(R.id.txtPswdRes);
        btnSignIn = findViewById(R.id.btnSignIn);
        create_acc = (TextView) findViewById(R.id.create_acc);
        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResLoginActivity.this, ResLoginActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin(){
        resEmail = txtEmailRes.getText().toString();
        resPswd = txtPswdRes.getText().toString();

        if(resEmail.isEmpty()){
            txtEmailRes.setError("Email is required!");
            txtEmailRes.requestFocus();
            return;
        }

        if(resPswd.isEmpty()){
            txtPswdRes.setError("Password is required!");
            txtPswdRes.requestFocus();
            return;
        }

        if(resPswd.length() <6){
            txtPswdRes.setError("Password is short");
            txtPswdRes.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(resEmail,resPswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Sign in success, update UI with the signed-in user's information
                if(task.isSuccessful()){
                    Toast.makeText(ResLoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();

                    //direct go to homepage
                    startActivity(new Intent(ResLoginActivity.this, homeRescuer.class));
                }else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(ResLoginActivity.this, "Login Unsuccessfully, Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

