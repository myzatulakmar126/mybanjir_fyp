package com.example.mybanjir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listSosActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    sosAdapter sosAdapter;
    ArrayList<sos> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listsos);

        recyclerView = (RecyclerView) findViewById(R.id.listsos);
        database = FirebaseDatabase.getInstance().getReference("sos");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        sosAdapter = new sosAdapter(this,list);
        recyclerView.setAdapter(sosAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    sos s;
                    s = dataSnapshot.getValue(sos.class);
                    //sos.setSosId(dataSnapshot.getKey());
                    list.add(s);

                }
                sosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}