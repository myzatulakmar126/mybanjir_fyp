package com.example.mybanjir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdLoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView create_acc;
    EditText txtEmailAd, txtPswdAd;
    Button btnSignIn;

    String adEmail, adPswd;
    private FirebaseAuth mAuth;
    Admin ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adlogin);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        txtEmailAd = findViewById(R.id.txtEmailAd);
        txtPswdAd = findViewById(R.id.txtPswdAd);
        btnSignIn = findViewById(R.id.btnSignIn);
        create_acc = findViewById(R.id.create_acc);

        btnSignIn.setOnClickListener(this);
        create_acc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_acc:
                startActivity(new Intent(AdLoginActivity.this, AdRegisterActivity.class));
                break;
            case R.id.btnSignIn:
                checkLogin();
                break;
        }
    }

    private void checkLogin() {
        adEmail = txtEmailAd.getText().toString();
        adPswd = txtPswdAd.getText().toString();

        if (adEmail.isEmpty()) {
            txtEmailAd.setError("Email is required!");
            txtEmailAd.requestFocus();
            return;
        }
        if (adPswd.isEmpty()) {
            txtPswdAd.setError("Password is required!");
            txtEmailAd.requestFocus();
            return;
        }
        if (adPswd.length() < 6) {
            txtPswdAd.setError("Password is short");
            txtPswdAd.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(adEmail, adPswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AdLoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();

                    //direct go to homepage
                    startActivity(new Intent(AdLoginActivity.this, AdRegisterActivity.class));
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(AdLoginActivity.this, "Unsuccessfully login!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}