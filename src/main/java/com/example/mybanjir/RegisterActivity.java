package com.example.mybanjir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView acc_exist;
    EditText txtEmail, txtPswd, txtRepass;
    Button btnSignUp;
    Spinner spinRoles;
    String item;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    user user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPswd = (EditText) findViewById(R.id.txtPswd);
        txtRepass = (EditText) findViewById(R.id.txtRepass);
        spinRoles = (Spinner) findViewById(R.id.spinRoles);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        acc_exist = (TextView) findViewById(R.id.acc_exist);
        acc_exist.setOnClickListener(this);
        //spinRoles.setOnItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getInstance().getReference();

        user = new user();

        //to make view list drop down on interface
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinRoles.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.acc_exist:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            case R.id.btnSignUp:
                signUp();
                break;
        }
    }

    private void signUp(){
        //get data from EditTexts into String variable
        final String userEmail = txtEmail.getText().toString();
        final String userPswd = txtPswd.getText().toString();
        final String repass = txtRepass.getText().toString();
        final String userRole = spinRoles.getSelectedItem().toString();
        //SaveValue(item);

        //check if user fill all the field
        if (userEmail.isEmpty() || userPswd.isEmpty() || userPswd.length() <6 || repass.isEmpty() || userRole.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else if (userPswd.equals(repass)) {
            Toast.makeText(RegisterActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
        }

        mAuth.createUserWithEmailAndPassword(userEmail, userPswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //check if user has success registered

                    String currentUser = mAuth.getCurrentUser().getUid();
                    HashMap userInfo = new HashMap();
                    userInfo.put("userEmail", txtEmail);
                    userInfo.put("userPswd", txtPswd);
                    userInfo.put("repass", txtRepass);
                    userInfo.put("userRole", spinRoles);

                    //create user object
                    user = new user(currentUser, userEmail, userPswd, repass, userRole);
                    //return id of registered user
                    FirebaseDatabase.getInstance().getReference("User").child(currentUser).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Something wrong somewheree", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

               // @Override
                //public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  // item = spinRoles.getSelectedItem().toString();

               // }

               // @Override
               // public void onNothingSelected(AdapterView<?> adapterView) {}
                 //void SaveValue(String item) {
                    //if(item == "Flood victim"){
                        //Toast.makeText(this, "Please select roles", Toast.LENGTH_SHORT).show();
                   // }else {
                       // user.setUserRole(item);
                       // String id = reference.push().getKey();
                       // reference.child(id).setValue(user);
                       // Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
                   // }
