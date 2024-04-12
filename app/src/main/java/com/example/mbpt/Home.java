package com.example.mbpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    Button btn1;
    TextView tv1;
    ImageView iv1;
    TextView tv3;
    ImageView link1;
    ImageView link2;
    ImageView link3;
    TextView tv2;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    protected void getId(){
        btn1=findViewById(R.id.btn1);
        tv1=findViewById(R.id.tv1);
        iv1=findViewById(R.id.iv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        link1=findViewById(R.id.link1);
        link2=findViewById(R.id.link2);
        link3=findViewById(R.id.link3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getId();
        String name="";
        String uid="";
        String type="";
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        if (source != null && source.equals("signIn")) {
            name =intent.getStringExtra("name");
            uid=intent.getStringExtra("uid");
        }else if(source !=null && source.equals("register")){
            name =intent.getStringExtra("name");
            uid=intent.getStringExtra("uid");
        }

        // taking data from the firebase
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("userPersonality");
        databaseReference.child(uid).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    personalityData pd = dataSnapshot.getValue(personalityData.class);// set the information and data about personality
                    tv3.setText(pd.personalityType);

                }else{
                    Toast.makeText(getApplicationContext(),"Failed to get any previous personality test's result",Toast.LENGTH_SHORT).show();
                    tv2.setText("Give Test !!!");
                    tv3.setText("Please give the test to see the results");

                }
            }
        });


        if (source != null && source.equals("test")) {
            type = intent.getStringExtra("personalityType");
        }


        tv1.setText(name.toUpperCase()+"\n"+uid+"\n"+type);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, signin.class);
                startActivity(i);
            }
        });
        final String uidCopy=uid;
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, test.class);
                i.putExtra("uid",uidCopy);
                startActivity(i);

            }
        });

        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, github.class);
                startActivity(i);
            }
        });
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, linkedin.class);
                startActivity(i);
            }
        });
        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, insta.class);
                startActivity(i);
            }
        });



    }
}