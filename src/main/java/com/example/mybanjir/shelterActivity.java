package com.example.mybanjir;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class shelterActivity extends AppCompatActivity {


    ImageView uploadimage;
    Button btnSubmit;
    EditText txtShelterName, txtAddress, txtPhoneNum;
    Spinner statRel;
    String imageURL;
    Uri uri;

    //request code
    private final int PICK_IMAGE_REQUEST = 22;

    //for firebase storage and storageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addshelter);

        uploadimage = findViewById(R.id.uploadimage);
        txtShelterName = findViewById(R.id.txtShelterName);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhoneNum = findViewById(R.id.txtPhoneNum);
        //statRel = findViewById(R.id.statRel);
        btnSubmit = findViewById(R.id.btnSubmit);
         reference = FirebaseDatabase.getInstance().getReference("relief_shelter").push();

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.statusShelter, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //statRel.setAdapter(adapter);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadimage.setImageURI(uri);
                    } else{
                            Toast.makeText(shelterActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                 }
        );

        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveData();
            }
        });
    }

    public void saveData(){
        // Create a Cloud Storage reference from the app
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Shelter images").child(uri.getLastPathSegment());

        AlertDialog.Builder builder =new AlertDialog.Builder(shelterActivity.this);
        builder.setCancelable(false);
        //builder.setView(R.layout.progress_layout);
        builder.setView(R.layout.layout_addshelter);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    public void uploadData(){
        String relName = txtShelterName.getText().toString();
        String relAddress = txtAddress.getText().toString();
        String relStatus = statRel.getSelectedItem().toString();
        String relNumPhone = txtPhoneNum.getText().toString();

        relief_shelter relief_shelter = new relief_shelter(relName,relAddress,relNumPhone,relStatus, imageURL);

       FirebaseDatabase.getInstance().getReference("relief_shelter").child(relName).setValue(relief_shelter).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(shelterActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
       }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Toast.makeText(shelterActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
           }
       });

   }
}