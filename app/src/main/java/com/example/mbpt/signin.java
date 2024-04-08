package com.example.mbpt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signin extends AppCompatActivity {
    Button btn1 ;
    Button btn2;
    TextInputEditText tiet2 ,tiet3;
    FirebaseDatabase database;
    DatabaseReference reference;
    private void findId(){
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        tiet2=findViewById(R.id.tiet2);
        tiet3=findViewById(R.id.tiet3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        findId();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("user");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1=new Intent(signin.this, register.class);
                startActivity(int1);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=tiet2.getText().toString();
                String password=tiet3.getText().toString();
                if(uid.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter The UID",Toast.LENGTH_SHORT).show();
                }else{
                    reference.child(uid).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                registerData rd=dataSnapshot.getValue(registerData.class);
                                if(rd.password.equals(password)){
                                    Intent intent=new Intent(signin.this,Home.class);
                                    intent.putExtra("name",rd.name);
                                    intent.putExtra("uid",rd.uid);
                                    Toast.makeText(getApplicationContext(),"Signed In successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Wrong Password !!! Try Again",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(),"Sorry , UID not found ",Toast.LENGTH_SHORT).show();
                            tiet3.setText("");
                            tiet2.setText("");

                        }
                    });
                }
            }
        });

    }
}