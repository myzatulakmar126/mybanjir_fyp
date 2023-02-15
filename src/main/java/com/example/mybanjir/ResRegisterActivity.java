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

public class ResRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView acc_exist;
    EditText txtEmailRes,txtPswdRes, txtRepassRes;
    Button btnSignUp;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    rescuer res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resregister);

        txtEmailRes = (EditText) findViewById(R.id.txtEmailRes);
        txtPswdRes = (EditText) findViewById(R.id.txtPswdRes);
        txtRepassRes = (EditText) findViewById(R.id.txtRepassRes);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        acc_exist = (TextView) findViewById(R.id.acc_exist);
        acc_exist.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        reference = database.getInstance().getReference().child("Rescuer");

        res = new rescuer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.acc_exist:
                startActivity(new Intent(ResRegisterActivity.this, ResLoginActivity.class));
                break;
            case R.id.btnSignUp:
                signUp();
                break;
        }
    }

    private void signUp(){
       final String resEmail = txtEmailRes.getText().toString();
       final String resPswd = txtPswdRes.getText().toString();
       final String resrepass = txtRepassRes.getText().toString();

       //check user if user fill all the field
        if(resEmail.isEmpty() || resPswd.isEmpty() || resPswd.length() <6 || resrepass.isEmpty()) {
            Toast.makeText(this, "Please fill empty field", Toast.LENGTH_SHORT).show();
        }
        else if(resPswd.equals(resrepass)) {
            Toast.makeText(this, "Pssword are not matching", Toast.LENGTH_SHORT).show();
        }

        mAuth.createUserWithEmailAndPassword(resEmail, resPswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String currentUser = mAuth.getCurrentUser().getUid();
                    HashMap resInfo = new HashMap();
                    resInfo.put("resEmail", txtEmailRes);
                    resInfo.put("resPswd", txtPswdRes);
                    resInfo.put("resrepass", txtRepassRes);

                    //create user object
                    res = new rescuer(currentUser,resEmail,resPswd,resrepass);

                    //return id of registered user
                    FirebaseDatabase.getInstance().getReference("Rescuer").child(currentUser).setValue(res).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResRegisterActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResRegisterActivity.this, ResLoginActivity.class));
                            }
                            else {
                                Toast.makeText(ResRegisterActivity.this, "Unsuccesfull Registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}