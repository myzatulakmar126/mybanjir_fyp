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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView acc_exist;
    EditText txtEmailAd, txtPswdAd, txtRepassAd;
    Button btnSignUp;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    Admin ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adregister);

        txtEmailAd = (EditText) findViewById(R.id.txtEmailAd);
        txtPswdAd = (EditText) findViewById(R.id.txtPswdAd);
        txtRepassAd = (EditText) findViewById(R.id.txtRepassAd);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        acc_exist = (TextView) findViewById(R.id.acc_exist);

        acc_exist.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getInstance().getReference().child("Admin");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.acc_exist:
                startActivity(new Intent(AdRegisterActivity.this,AdLoginActivity.class));
                break;
            case R.id.btnSignUp:
                signUp();
                break;
        }
    }

    private void signUp(){
        //get data from EditTexts into String variable
        final String adEmail = txtEmailAd.getText().toString();
        final String adPswd = txtPswdAd.getText().toString();
        final String adrepass = txtRepassAd.getText().toString();

        //check if user fill all the field
        if(adEmail.isEmpty() || adPswd.isEmpty() || adPswd.length() <6 ||adrepass.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else if (adPswd.equals(adrepass)){
            Toast.makeText(this, "Password is not match", Toast.LENGTH_SHORT).show();
        }

        mAuth.createUserWithEmailAndPassword(adEmail,adPswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String currentUser = mAuth.getCurrentUser().getUid();
                    HashMap adInfo = new HashMap();
                    adInfo.put("adEmail", txtEmailAd);
                    adInfo.put("adPswd", txtPswdAd);
                    adInfo.put("adrepass", txtRepassAd);

                    //create admin object
                    ad = new Admin(currentUser,adEmail, adPswd, adrepass);

                    //return id
                    FirebaseDatabase.getInstance().getReference("Admin").child(currentUser).setValue(ad).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AdRegisterActivity.this, "Succesffully registered!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdRegisterActivity.this,AdLoginActivity.class));
                            }
                            else {
                                Toast.makeText(AdRegisterActivity.this, "Registeration Unsuccessfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(AdRegisterActivity.this, "Something wrong somewhere", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}