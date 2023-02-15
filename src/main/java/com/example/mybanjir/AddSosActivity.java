package com.example.mybanjir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSosActivity extends AppCompatActivity {

    EditText txtfvName, txtfvAddress, txtRemarkfv,txtRemarkres;
    Button btnSubmit;
    Spinner spinHelp, spinStatus;
    FirebaseDatabase database;
    DatabaseReference reference;
    private String sosId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addsos);

        txtfvName = (EditText) findViewById(R.id.txtfvName);
        txtfvAddress = (EditText) findViewById(R.id.txtfvAddress);
        txtRemarkfv = (EditText) findViewById(R.id.txtRemarkfv);
        txtRemarkres = (EditText) findViewById(R.id.txtRemarkres);
        spinHelp = (Spinner) findViewById(R.id.spinHelp);
        spinStatus = (Spinner) findViewById(R.id.spinStatus);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("sos");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.help, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinHelp.setAdapter(adapter);

        ArrayAdapter <CharSequence> adapterStat = ArrayAdapter.createFromResource(this,R.array.status, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinStatus.setAdapter(adapterStat);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fvName = txtfvName.getText().toString();
                String sosAddress = txtfvAddress.getText().toString();
                String typeSos = spinHelp.getSelectedItem().toString();
                String sosStatus = spinStatus.getSelectedItem().toString();
                String victimRemark = txtRemarkfv.getText().toString();
                String rescuerRemark = txtRemarkres.getText().toString();
                sosId = fvName;

                //create sos object
                sos s = new sos(sosId, fvName, sosAddress, typeSos, sosStatus, victimRemark, rescuerRemark);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        reference.child(sosId).setValue(s);
                        Toast.makeText(AddSosActivity.this, "Successfully Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddSosActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddSosActivity.this, "Unsuccessfully added" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

                //if(fvName.isEmpty() || sosAddress.isEmpty() || typeSos.isEmpty() || sosStatus.isEmpty() || victimRemark.isEmpty()) {
                // Toast.makeText(AddSosActivity.this, "Please Enter all data", Toast.LENGTH_SHORT).show();
            }       // }
      });
   }
}