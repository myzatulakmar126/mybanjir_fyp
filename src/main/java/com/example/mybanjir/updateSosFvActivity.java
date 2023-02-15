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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class updateSosFvActivity extends AppCompatActivity {

    TextView txtRemarkres;
    EditText txtfvName, txtfvAddress, txtRemarkfv;
    Button btnUpdate, btnDel;
    Spinner spinHelp, spinStatus;
    FirebaseDatabase database;
    DatabaseReference reference;
    private String sosId;
    private sos s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatefvsos_layout);

        txtRemarkres = (TextView) findViewById(R.id.txtRemarkres);
        txtfvName = (EditText) findViewById(R.id.txtfvName);
        txtfvAddress = (EditText) findViewById(R.id.txtfvAddress);
        txtRemarkfv = (EditText) findViewById(R.id.txtRemarkfv);
        spinHelp = (Spinner) findViewById(R.id.spinHelp);
        spinStatus = (Spinner) findViewById(R.id.spinStatus);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDel = (Button) findViewById(R.id.btnDel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.help, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinHelp.setAdapter(adapter);

        ArrayAdapter <CharSequence> adapterStat = ArrayAdapter.createFromResource(this,R.array.status, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinStatus.setAdapter(adapterStat);

        s= getIntent().getParcelableExtra("sos");
        if(s!=null){
            txtRemarkfv.setText(s.getFvName());
            txtfvAddress.setText(s.getSosAddress());
            txtRemarkfv.setText(s.getVictimRemark());
            sosId = s.getSosId();
        }

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("sos").child(sosId);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fvName = txtfvName.getText().toString();
                String sosAddress = txtfvAddress.getText().toString();
                String typeSos = spinHelp.getSelectedItem().toString();
                String sosStatus = spinStatus.getSelectedItem().toString();
                String victimRemark = txtRemarkfv.getText().toString();
                String rescueRemark = txtRemarkres.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("fvName",fvName);
                map.put("sosAddress",sosAddress);
                map.put("typeSos",typeSos);
                map.put("sosStatus",sosStatus);
                map.put("victimRemark",victimRemark);
                map.put("rescueRemark", rescueRemark);
                map.put("sosId", sosId);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        reference.updateChildren(map);
                        Toast.makeText(updateSosFvActivity.this, "Information Updated!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(updateSosFvActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(updateSosFvActivity.this, "Fail to update!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSos();
            }
        });
    }

    private void deleteSos(){
        reference.child(sosId).removeValue();
        Toast.makeText(this, "Sos Deleted!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(updateSosFvActivity.this,HomeActivity.class));
        finish();
    }
}