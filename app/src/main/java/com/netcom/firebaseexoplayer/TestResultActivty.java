package com.netcom.firebaseexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static android.graphics.Color.BLUE;

public class TestResultActivty extends AppCompatActivity {
    TextView timerResult;
    TextView test_results;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result_activty);

        timerResult = findViewById(R.id.timerResultId);


        String sDuration = getIntent().getStringExtra("time");
        timerResult.setText(sDuration);

        int scored=getIntent().getExtras().getInt("score");
        String score_result=Integer.toString(scored);
        test_results=findViewById(R.id.result);
        test_results.setText(score_result);
        btn=findViewById(R.id.button_snack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smack(v);
            }
        });
    }
      private void smack(View v){

          Intent i = new Intent(TestResultActivty.this , VideoList.class);
          startActivity(i);

//          Snackbar snackbar=Snackbar.make(v,"Test Completed",Snackbar.LENGTH_SHORT );
//          snackbar.show();

      }
}