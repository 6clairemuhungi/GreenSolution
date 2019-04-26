package com.ann.greensolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganisationActivity extends AppCompatActivity {

    @BindView(R.id.txtfullnames)
    EditText inputNames;
    @BindView(R.id.txtemail2)
    EditText inputEmail;
    @BindView(R.id.txtnumber2)
    EditText inputPhone;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);
        ButterKnife.bind(this);
        db= FirebaseDatabase.getInstance().getReference("users");
    }

    public void register(View view) {
       // Intent x = new Intent(OrganisationActivity.this, DisplayActivity.class);
       // startActivity(x);
        String names= inputNames.getText().toString().trim();
        String email= inputEmail.getText().toString().trim();
        String phone= inputPhone.getText().toString().trim();
        if (names.isEmpty()|| email.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Fill in all values", Toast.LENGTH_SHORT).show();
            return;
        }
        Advisors advisors=new Advisors(names, email, phone);
        progressBar.setVisibility(View.VISIBLE);
        db.push().setValue(advisors).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(OrganisationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                inputEmail.setText("");
                inputNames.setText("");
                inputPhone.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(OrganisationActivity.this, "Failed To save", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void advisors(View view) {
        Intent x = new Intent(OrganisationActivity.this, DisplayActivity.class);
        startActivity(x);
    }
}
