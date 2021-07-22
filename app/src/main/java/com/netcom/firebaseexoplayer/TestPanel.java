package com.netcom.firebaseexoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.TimeUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.netcom.firebaseexoplayer.R;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class TestPanel extends AppCompatActivity {
    private TextView question, no_indicator, correct_answer_pop_up;
    private RelativeLayout options_container;
    private int count = 0;
    private Button btn, correct_option;
    private List<model> QuestionList;
    private int position = 0;
    private int score = 0;
    String mystring = "Your score is:";
    
    TextView timerText ;
    long duration = TimeUnit.MINUTES.toMillis(1);
    Long sender ;
    String str ;

    Chronometer chronometer;
    long stopTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        chronometer = findViewById(R.id.chronometerId);
        chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
        chronometer.start();

        timerText = findViewById(R.id.timerId);

        CountDownTimer countDownTimer =  new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String sDuration = format(Locale.ENGLISH , "%02d:%02d" , TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) , TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) );
                timerText.setText(sDuration);

                int elapsedMillis = (int) (SystemClock.elapsedRealtime() - chronometer.getBase());



                long timeDifference = elapsedMillis/1000;
                int h = (int) (timeDifference / (3600));
                int m = (int) ((timeDifference - (h * 3600)) / 60);
                int s = (int) (timeDifference - (h * 3600) - m * 60);

                str =  String.format("%02d:%02d", m,s);

                System.out.println("************** " + str + " ****************");
            }

            @Override
            public void onFinish() {
                Toast toast =  Toast.makeText(getApplicationContext() , "Time Up \n You are scored : "+ score  , Toast.LENGTH_LONG);
                View toastView = toast.getView();
                TextView toastMessage =  toastView.findViewById(android.R.id.message);
                toastMessage.setTextSize(20);
                toastMessage.setTextColor(Color.RED);
                toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.alarm_clock, 0, 0, 0);
                toastMessage.setGravity(Gravity.CENTER_HORIZONTAL);
                toastMessage.setCompoundDrawablePadding(16);
                toastView.setBackgroundColor(Color.CYAN);
                toast.show();



//                Snackbar.make(findViewById(android.R.id.content) , "You are scored : "+ score  , Snackbar.LENGTH_LONG)
//                        .setAction("Try again!", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                startActivity(getIntent()); finish();
//                            }
//                        }).setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
//                        .show();

                onBackPressed();
//                startActivity(new Intent(getApplicationContext() , TestResultActivty.class));

            }

        }.start();

        question = findViewById(R.id.question);
        no_indicator = findViewById(R.id.count);
        options_container = findViewById(R.id.options_container);
        btn = findViewById(R.id.submit_btn);
        QuestionList = new ArrayList<>();
        QuestionList.add(new model("1) A system of government by one person with absolute power.", "(a) Aristocracy", "(b) Theocracy", "(c) Democracy", "(d) Autocracy", "(d) Autocracy"));
        QuestionList.add(new model("2)When a country is governed by a few privileged, the form of government is\n" +
                "called\n", "(a) Oligarchy", "(b) Parliamentary", "(c) Democracy", "(d) Republic", "(d) Republic"));
        QuestionList.add(new model("3)Abraham Lincoln was the President of the ________.", "(a) USA", "(b) UK", "(c) USSR", "(d) India", "(a) USA"));
        QuestionList.add(new model("4)Direct Democracy in olden times existed", "(a) In the republics of ancient India", "(b) Among the USA", "(c) In the city-state of ancient Athens", "(d) Among the UK", "(c) In the city-state of ancient Athens"));
        QuestionList.add(new model("5)From which language was the term“Democracy” derived?", "(a) Greek", "(b) Latin", "(c) Persian", "(d) Arabic", "(a) Greek"));
        QuestionList.add(new model("6)In democracy the final authority rests with", "(a) The Parliament", "(b) The People", "(c) The council of Ministers", "(d) The President", "(d) The President"));
        for (int i = 0; i < 4; i++) {
            options_container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    check_answer((Button) v);
                }
            });
        }
        playanim(question, 0, QuestionList.get(position).getQuestion());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(false);
                btn.setAlpha(0);
                enableOption(true);
//                System.out.println("Position "+ position +QuestionList.size());
                if (position == QuestionList.size()) {
                    Intent i = new Intent(TestPanel.this, TestResultActivty.class);
                    i.putExtra("score", score);
                    i.putExtra("time" , str);
                    chronometer.stop();
                    countDownTimer.cancel();
                    startActivity(i);
                    return;
//                    Toast.makeText(getApplicationContext(),"Test Finished. "+"Your Score is "+score,Toast.LENGTH_LONG).show();
//                    return;
                }
                count = 0;
                try {
                    playanim(question, 0, QuestionList.get(position).getQuestion());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Test Finished", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void bottom_sheet_answer_popup() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TestPanel.this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(TestPanel.this).inflate(R.layout.answer, findViewById(R.id.answer_sheet_layout));
        correct_answer_pop_up = bottomSheetView.findViewById(R.id.correct_answer_dialog_sheet);
        correct_answer_pop_up.setText(QuestionList.get(position).getCorrect_answer());
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
        position++;
    }

    private void check_answer(Button selectedOption) {
        enableOption(false);
        btn.setEnabled(true);
        btn.setAlpha(1);
        if (selectedOption.getText().toString().equals(QuestionList.get(position).getCorrect_answer())) {
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#a8dda8")));
        } else {
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#e97878")));
            correct_option = options_container.findViewWithTag(QuestionList.get(position).getCorrect_answer());
            correct_option.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        }
        bottom_sheet_answer_popup();
    }

    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            options_container.getChildAt(i).setEnabled(enable);
            if (enable) {
                options_container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

            }
        }
    }

    private void playanim(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                if (value == 0 && count < 4) {
                    String options = "";
                    if (count == 0) {
                        options = QuestionList.get(position).getOption1();
                    } else if (count == 1) {
                        options = QuestionList.get(position).getOption2();
                    } else if (count == 2) {
                        options = QuestionList.get(position).getOption3();
                    } else if (count == 3) {
                        options = QuestionList.get(position).getOption4();
                    }
                    playanim(options_container.getChildAt(count), 0, options);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        String question_count_text = "Question No. :  " + (position + 1) + "/" + QuestionList.size();
                        no_indicator.setText(question_count_text);
                    } catch (ClassCastException exception) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playanim(view, 1, data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}