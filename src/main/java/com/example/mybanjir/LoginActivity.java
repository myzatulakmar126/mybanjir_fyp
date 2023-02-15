package com.example.mybanjir;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    TextView create_acc;
    EditText txtEmail, txtPswd;
    Button btnSignIn;

    String userEmail, userPswd;
    private FirebaseAuth mAuth;
    user u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.txtEmail);
        txtPswd = findViewById(R.id.txtPswd);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
        create_acc = (TextView) findViewById(R.id.create_acc);
        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void checkLogin() {
        userEmail = txtEmail.getText().toString();
        userPswd = txtPswd.getText().toString();

        if (userEmail.isEmpty()) {
            txtEmail.setError("Email is required!");
            txtEmail.requestFocus();
            return;
        }
        if (userPswd.isEmpty()) {
            txtPswd.setError("Password is required!");
            txtPswd.requestFocus();
            return;
        }
        if (userPswd.length() < 6) {
            txtPswd.setError("Password is short");
            txtPswd.requestFocus();
            return;
        } else {

            mAuth.signInWithEmailAndPassword(userEmail, userPswd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Sign in success, update UI with the signed-in user's information
                                Toast.makeText(LoginActivity.this, "Login Successfull!", Toast.LENGTH_SHORT).show();

                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference reference = firebaseDatabase.getReference().child("User");

                                //check if user has success registered

                                reference.orderByChild("userName").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            Log.d("User exists", "Welcome!");
                                            String value = snapshot.child("userRole").getValue().toString();

                                            if (value.equals("Flood Victim") && value != null) {
                                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                                finish();
                                            } else if (value.equals("Rescuer")) {
                                                startActivity(new Intent(LoginActivity.this, homeRescuer.class));
                                                finish();
                                            } else {
                                                startActivity(new Intent(LoginActivity.this, homeFv.class));
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                //if (u.getUserRole().equals("Flood Victim")) {
                                //direct go to homepage
                                //startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                //startActivity(new Intent(LoginActivity.this, homeFv.class));
                                //} else if (u.getUserRole().equals("Rescuer")) {
                                //direct go to homepage
                                // startActivity(new Intent(LoginActivity.this, homeRescuer.class));

                                // } else {
                                // If sign in fails, display a message to the user.
                                //Toast.makeText(LoginActivity.this, "Login Unsuccessfully, Please Check your email and password", Toast.LENGTH_SHORT).show();
                                // }
                                //}
                            }else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            // @Override
                            //public void onCancelled(@NonNull DatabaseError error) {
                            //failed to read value
                            //Toast.makeText(LoginActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();

                            //}
                            // });
                            // Sign in success, update UI with the signed-in user's information
                            //  Toast.makeText(LoginActivity.this, "Login Successfull!", Toast.LENGTH_SHORT).show();
                            //direct go to homepage
                            //  startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            // } else {
                            // If sign in fails, display a message to the user.
                            // Toast.makeText(LoginActivity.this, "Login Unsuccessfully, Please Check your email and password", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
                //});
//    }
//}
