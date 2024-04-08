package com.example.mbpt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    DatabaseReference reference;
    Button btn1;
    Button btn2;
    TextInputEditText tiet1;
    TextInputEditText tiet2;
    TextInputEditText tiet3;
    FirebaseDatabase database;
    public void findId(){
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        tiet1=findViewById(R.id.tiet1);
        tiet2=findViewById(R.id.tiet2);
        tiet3=findViewById(R.id.tiet3);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("user");
        findId();
        btn2.setOnClickListener(new View.OnClickListener() {
            int y=0;
            @Override
            public void onClick(View view) {
                if(tiet1.getText().toString().isEmpty() || tiet2.getText().toString().isEmpty() || tiet3.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter data in all the fields",Toast.LENGTH_SHORT).show();
                }else{
                    registerData rd=new registerData(tiet1.getText().toString(),tiet2.getText().toString(),tiet3.getText().toString());
                    String uid=tiet2.getText().toString();
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(uid)){
                                Toast.makeText(getApplicationContext(),"This ID exists already >>> Try Another",Toast.LENGTH_LONG).show();
                            }else{
                                y=1;

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Failed ,Sorry :(", Toast.LENGTH_SHORT).show();

                        }
                    });
                    if(y==1){
                        reference.child("user").child(uid).setValue(rd).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                tiet1.setText("");
                                tiet2.setText("");
                                tiet3.setText("");

                                Toast.makeText(getApplicationContext(),"registered successfully :) ",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed ,Sorry :(", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(register.this, signin.class);
                startActivity(intent);
                finish();
            }
        });


    }
}