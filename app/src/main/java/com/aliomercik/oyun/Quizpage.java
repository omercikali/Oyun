package com.aliomercik.oyun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quizpage extends AppCompatActivity {
    TextView timetw, correcttw, wrongrw, question, a, b, c, d;
    Button finisbtn, nextbtnn;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Oyun");

    String quızQuestion, quizAnswera, quizAnswerb, quizAnswerc, quizAnswerd, quızCorrectAnswer;
    int questionCount;
    int questionNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);
        timetw = findViewById(R.id.timetw);
        correcttw = findViewById(R.id.correctanswertwt);
        wrongrw = findViewById(R.id.wronganswertw);
        question = findViewById(R.id.twquestion);
        a = findViewById(R.id.a);
        b = findViewById(R.id.twb);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        finisbtn = findViewById(R.id.finishbtn);
        nextbtnn = findViewById(R.id.nextbtn);

        game();

        nextbtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game();
            }
        });
        finisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void game() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionCount = (int) dataSnapshot.getChildrenCount();
                quızQuestion = dataSnapshot.child("oyun").child(String.valueOf(questionNumber)).child("q").getValue().toString();
                quizAnswera = dataSnapshot.child("oyun").child(String.valueOf(questionNumber)).child("a").getValue().toString();
                quizAnswerb = dataSnapshot.child("oyun").child(String.valueOf(questionNumber)).child("b").getValue().toString();
                quizAnswerc = dataSnapshot.child("oyun").child(String.valueOf(questionNumber)).child("c").getValue().toString();
                quizAnswerd = dataSnapshot.child("oyun").child(String.valueOf(questionNumber)).child("d").getValue().toString();
                quızCorrectAnswer = dataSnapshot.child("oyun").child(String.valueOf(questionNumber)).child("answer").getValue().toString();

                question.setText(quızQuestion);
                a.setText(quizAnswera);
                b.setText(quizAnswerb);
                c.setText(quizAnswerc);
                d.setText(quizAnswerd);


                if (questionNumber < questionCount) {
                    questionNumber++;
                } else
                    Toast.makeText(Quizpage.this, "tüm sorular cevaplandı", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Quizpage.this, "bir problem oluştu", Toast.LENGTH_LONG).show();
            }
        });


    }
}