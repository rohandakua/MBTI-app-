package com.example.mbpt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class test extends AppCompatActivity {
    public ques[] arr = new ques[70];
    public int index = 0;
    Spinner spinner1;
    TextView tv1;
    Button btn1;
    TextView tvHowManyAttempted;
    TextView tvhowManySkipped;
    TextView ques;
    RadioGroup crg1;
    RadioButton opt1;
    RadioButton opt2;
    Button prev;
    Button next;

    qrd[] quesReport;
    String[] qr;
    int quesAttempted=0;
    int howManySkipped=0;
    FirebaseDatabase database ;
    DatabaseReference databaseReference;



    public void ffgetId(){
        spinner1=findViewById(R.id.spinner1);
        tv1=findViewById(R.id.tv1);
        btn1=findViewById(R.id.btn1);
        tvHowManyAttempted=findViewById(R.id.tvHowManyAttempted);
        tvhowManySkipped=findViewById(R.id.tvhowManySkipped);
        ques=findViewById(R.id.ctv1);
        opt1=findViewById(R.id.crb1);
        opt2=findViewById(R.id.crb2);
        prev=findViewById(R.id.prev);
        next=findViewById(R.id.next);
        crg1=findViewById(R.id.crg1);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        intialize();
        ffgetId();
        setQues();
        String uid=getIntent().getStringExtra("uid").toString();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("userPersonality");
        ArrayAdapter adapter=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,qr);

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                evaluate();
                index=i;
                displayQuestion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                displayQuestion();

            }
        });

        //submitt btn1
        btn1.setOnClickListener(new View.OnClickListener() {
            int what=0;
            @Override
            public void onClick(View view) {
                if(quesAttempted==70) {
                    String personalityType = calculate();
                    Log.d("personality",personalityType);

                    // making changes here
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(uid)){

                                what=1;
                            }else{
                                what=2;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(),"Error in accessing database",Toast.LENGTH_SHORT).show();

                        }
                    });


                    personalityData pd = new personalityData(uid,personalityType);
                    // making changes
                    if(what==1){
                        DatabaseReference db=database.getReference("userPersonality/"+uid);
                        db.setValue(pd);

                    } else if (what==2) {
                        databaseReference.child(uid).setValue(pd).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(),"Successfully updated :)",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Sorry Error 402 :(",Toast.LENGTH_SHORT).show();
                        }
                    });


                    }

                    Intent i = new Intent(test.this, Home.class);
                    i.putExtra("source","test");
                    i.putExtra("personalityType" , personalityType);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Please answer all 70 questions",Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluate();
                index++;
                if(index==70){
                    btn1.performClick();
                }else{
                    displayQuestion();

                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluate();
                index--;
                displayQuestion();

            }
        });




    }
    public class ansData{
        public int a;
        public int b;

    }
    public String calculate(){
        ansData col1=new ansData(),col2=new ansData(),col3=new ansData(),col4=new ansData(),col5=new ansData(),col6=new ansData(),col7=new ansData();
        for(int i=0;i<65;i=i+7){
            col1.a += (arr[i].option == 0) ? 1 : 0 ;
            col1.b += (arr[i].option == 0) ? 0 : 1 ;

            col2.a += (arr[i+1].option == 0) ? 1 : 0 ;
            col2.b += (arr[i+1].option == 0) ? 0 : 1 ;

            col3.a += (arr[i+2].option == 0) ? 1 : 0 ;
            col3.b += (arr[i+2].option == 0) ? 0 : 1 ;

            col4.a += (arr[i+3].option == 0) ? 1 : 0 ;
            col4.b += (arr[i+3].option == 0) ? 0 : 1 ;

            col5.a += (arr[i+4].option == 0) ? 1 : 0 ;
            col5.b += (arr[i+4].option == 0) ? 0 : 1 ;

            col6.a += (arr[i+5].option == 0) ? 1 : 0 ;
            col6.b += (arr[i+5].option == 0) ? 0 : 1 ;

            col7.a += (arr[i+6].option == 0) ? 1 : 0 ;
            col7.b += (arr[i+6].option == 0) ? 0 : 1 ;


        }

        int e = 0 ,i = 0 , s = 0 , n = 0 , t = 0 , f = 0 , j = 0 , p = 0 ;
        e = col1.a ;
        i = col1.b ;
        s = col2.a + col3.a ;
        n = col2.b + col3.b ;
        t = col4.a + col5.a ;
        f = col4.b + col5.b ;
        j = col6.a + col7.a ;
        p = col6.b + col7.b ;

        StringBuilder personality = new StringBuilder() ;
        personality.append( (e>i) ? 'E' : 'I' );
        personality.append( (s>n) ? 'S' : 'N' );
        personality.append( (t>f) ? 'T' : 'F' );
        personality.append( (j>p) ? 'J' : "P" );
        return personality.toString();






    }
    public void displayQuestion(){
        tv1.setText("Ques no - "+(index+1));
        tvHowManyAttempted.setText("Attempted - "+quesAttempted+"/70");
        tvhowManySkipped.setText("Skipped - "+howManySkipped+"/70");
        if(index <quesReport.length && quesReport[index].attempt==false){
            crg1.clearCheck();

        }else{
            if(index <quesReport.length && arr[index].option==0){
                opt1.setChecked(true);
            }else{
                opt2.setChecked(true);
            }
        }



        ques.setText(arr[index].q);
        opt1.setText(arr[index].a);
        opt2.setText(arr[index].b);

    }
    public void evaluate(){

        int selected= crg1.getCheckedRadioButtonId();
        if(index <quesReport.length && quesReport[index].attempt==false) {


            if (selected != -1) {
                RadioButton select = findViewById(selected);
                String selectedOption = select.getText().toString();
                if (index <quesReport.length && arr[index].a.equals(selectedOption)) {
                    arr[index].option = 0;
                } else if(index <quesReport.length ) {
                    arr[index].option = 1;
                }

                if (index <quesReport.length && quesReport[index].saw == true) {
                    quesReport[index].attempt = true;
                    howManySkipped--;
                    quesAttempted++;
                } else if(index <quesReport.length){
                    quesReport[index].saw = true;
                    quesReport[index].attempt = true;
                    quesAttempted++;

                }

            } else {
                if (index <quesReport.length && quesReport[index].saw == true) {
                    quesReport[index].attempt = false;

                } else if(index <quesReport.length ){
                    quesReport[index].saw = true;
                    quesReport[index].attempt = false;
                    howManySkipped++;

                }

            }
        }else {
            if (selected != -1) {
                RadioButton select = findViewById(selected);
                String selectedOption = select.getText().toString();
                if (index <quesReport.length && arr[index].a.equals(selectedOption)) {
                    arr[index].option = 0;
                } else if(index <quesReport.length ) {
                    arr[index].option = 1;
                }
            }


        }
    }


    public void intialize(){
        quesReport=new qrd[70];
        qr=new String[70];
        for(int i=1;i<71;i++){
            quesReport[i-1]=new qrd(i);
            qr[i-1]=Integer.toString(i);
        }

    }



    public void setQues() {

        arr[0] = new ques("1. At a party do you:", "a. Interact with many, including strangers", "b. Interact with a few, known to you");
        arr[1] = new ques("2. Are you more:", "a. Realistic than speculative", "b. Speculative than realistic");
        arr[2] = new ques("3. Is it worse to:", "a. Have your “head in the clouds”", "b. Be “in a rut”");
        arr[3] = new ques("4. Are you more impressed by:", "a. Principles", "b. Emotions");
        arr[4] = new ques("5. Are more drawn toward the:", "a. Convincing", "b. Touching");
        arr[5] = new ques("6. Do you prefer to work:", "a. To deadlines", "b. Just “whenever”");
        arr[6] = new ques("7. Do you tend to choose:", "a. Rather carefully", "b. Somewhat impulsively");
        arr[7] = new ques("8. At parties do you:", "a. Stay late, with increasing energy", "b. Leave early with decreased energy");
        arr[8] = new ques("9. Are you more attracted to:", "a. Sensible people", "b. Imaginative people");
        arr[9] = new ques("10. Are you more interested in:", "a. What is actual", "b. What is possible");
        //

        arr[10] = new ques("11. In judging others are you more swayed by:", "a. Laws than circumstances", "b. Circumstances than laws");
        arr[11] = new ques("12. In approaching others is your inclination to be somewhat:", "a. Objective", "b. Persona");
        arr[12] = new ques("13. Are you more:", "a. Punctual", "b. Leisurely");
        arr[13] = new ques("14. Does it bother you more having things:", "a. Incomplete", "b. Completed");
        arr[14] = new ques("15. In your social groups do you:", "a. Keep abreast of other’s happenings", "b. Get behind on the news");
        arr[15] = new ques("16. In doing ordinary things are you more likely to:", "a. Do it the usual way", "b. Do it your own way");
        arr[16] = new ques("17. Writers should:", "a. “Say what they mean and mean what they say”", "b. Express things more by use of analogy");
        arr[17] = new ques("18. Which appeals to you more:", "a. Consistency of thought", "b. Harmonious human relationships");
        arr[18] = new ques("19. Are you more comfortable in making:", "a. Logical judgments", "b. Value judgments");
        arr[19] = new ques("20. Do you want things:", "a. Settled and decided", "b. Unsettled and undecided");
        //

        arr[20] = new ques("21. Would you say you are more:", "a. Serious and determined", "b. Easy-going");
        arr[21] = new ques("22. In phoning do you:", "a. Rarely question that it will all be said", "b. Rehearse what you’ll say");
        arr[22] = new ques("23. Facts:", "a. “Speak for themselves”", "b. Illustrate principles");
        arr[23] = new ques("24. Are visionaries:", "a. somewhat annoying", "b. rather fascinating");
        arr[24] = new ques("25. Are you more often:", "a. a cool-headed person", "b. a warm-hearted person");
        arr[25] = new ques("26. Is it worse to be:", "a. unjust", "b. merciless");
        arr[26] = new ques("27. Should one usually let events occur:", "a. by careful selection and choice", "b. randomly and by chance");
        arr[27] = new ques("28. Do you feel better about:", "a. having purchased", "b. having the option to buy");
        arr[28] = new ques("29. In company do you:", "a. initiate conversation", "b. wait to be approached");
        arr[29] = new ques("30. Common sense is:", "a. rarely questionable", "b. frequently questionable");
        //

        arr[30] = new ques("31. Children often do not:", "a. make themselves useful enough", "b. exercise their fantasy enough");
        arr[31] = new ques("32. In making decisions do you feel more comfortable with:", "a. standards", "b. feelings");
        arr[32] = new ques("33. Are you more:", "a. firm than gentle", "b. gentle than firm");
        arr[33] = new ques("34. Which is more admirable:", "a. the ability to organize and be methodical", "b. the ability to adapt and make do");
        arr[34] = new ques("35. Do you put more value on:", "a. infinite", "b. open-minded");
        arr[35] = new ques("36. Does new and non-routine interaction with others", "a. stimulate and energize you", "b. tax your reserves");
        arr[36] = new ques("37. Are you more frequently:", "a. a practical sort of person", "b. a fanciful sort of person");
        arr[37] = new ques("38. Are you more likely to:", "a. see how others are useful", "b. see how others see");
        arr[38] = new ques("39. Which is more satisfying:", "a. to discuss an issue thoroughly", "b. to arrive at agreement on an issue");
        arr[39] = new ques("40. Which rules you more:", "a. your head", "b. your heart");
        //

        arr[40] = new ques("41. Are you more comfortable with work that is:", "a. contracted", "b. done on a casual basis");
        arr[41] = new ques("42. Do you tend to look for:", "a. the orderly", "b. whatever turns up");
        arr[42] = new ques("43. Do you prefer:", "a. many friends with brief contact", "b. a few friends with more lengthy contact");
        arr[43] = new ques("44. Do you go more by:", "a. facts", "b. principles");
        arr[44] = new ques("45. Are you more interested in:", "a. production and distribution", "b. design and research");
        arr[45] = new ques("46. Which is more of a compliment:", "a. “There is a very logical person.”", "b. “There is a very sentimental person.”");
        arr[46] = new ques("47. Do you value in yourself more that you are:", "a. unwavering", "b. devoted");
        arr[47] = new ques("48. Do you more often prefer the", "a. final and unalterable statement", "b. tentative and preliminary statement");
        arr[48] = new ques("49. Are you more comfortable:", "a. after a decision", "b. before a decisio");
        arr[49] = new ques("50. Do you", "a. speak easily and at length with strangers", "b. find little to say to strangers");
        //

        arr[50] = new ques("51. Are you more likely to trust your:", "a. experience", "b. hunch");
        arr[51] = new ques("52. Do you feel:", "a. more practical than ingenious", "b. more ingenious than practical");
        arr[52] = new ques("53. Which person is more to be complimented – one of:", "a. clear reason", "b. strong feeling");
        arr[53] = new ques("54. Are you inclined more to be:", "a. fair-minded", "b. sympathetic");
        arr[54] = new ques("55. Is it preferable mostly to:", "a. make sure things are arranged", "b. just let things happen");
        arr[55] = new ques("56. In relationships should most things be:", "a. re-negotiable", "b. random and circumstantial");
        arr[56] = new ques("57. When the phone rings do you:", "a. hasten to get to it first", "b. hope someone else will answer");
        arr[57] = new ques("58. Do you prize more in yourself", "a. a strong sense of reality", "b. a vivid imagination");
        arr[58] = new ques("59. Are you drawn more to:", "a. fundamentals", "b. overtones");
        arr[59] = new ques("60. Which seems the greater error:", "a. to be too passionate", "b. to be too objective");
        //

        arr[60] = new ques("61. Do you see yourself as basically:", "a. hard-headed", "b. soft-hearted");
        arr[61] = new ques("62. Which situation appeals to you more:", "a. the structured and scheduled", "b. the unstructured and unscheduled");
        arr[62] = new ques("63. Are you a person that is more:", "a. routinized than whimsical", "b. whimsical than routinized");
        arr[63] = new ques("64. Are you more inclined to be", "a. easy to approach", "b. somewhat reserved");
        arr[64] = new ques("65. In writings do you prefer:", "a. the more literal", "b. the more figurative");
        arr[65] = new ques("66. Is it harder for you to:", "a. identify with others", "b. utilize others");
        arr[66] = new ques("67. Which do you wish more for yourself:", "a. clarity of reason", "b. strength of compassion");
        arr[67] = new ques("68. Which is the greater fault:", "a. being indiscriminate", "b. being critical");
        arr[68] = new ques("69. Do you prefer the:", "a. planned event", "b. unplanned event");
        arr[69] = new ques("70. Do you tend to be more:", "a. deliberate than spontaneous", "b. spontaneous than deliberate");


    }
}