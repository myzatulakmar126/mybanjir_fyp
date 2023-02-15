package com.example.mybanjir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class roleActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFv, btnRes, btnAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_role);

        btnFv = (Button) findViewById(R.id.btnFv);
        btnRes = (Button) findViewById(R.id.btnRes);
        btnAd = (Button) findViewById(R.id.btnAd);

        btnRes.setOnClickListener(this);
        btnAd.setOnClickListener(this);
        btnFv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFv:
                startActivity(new Intent(roleActivity.this, RegisterActivity.class));
                break;
            case R.id.btnRes:
                startActivity(new Intent(roleActivity.this,ResRegisterActivity.class));
                break;
            case R.id.btnAd:
                startActivity(new Intent(roleActivity.this, AdRegisterActivity.class));
                break;
        }
    }
}