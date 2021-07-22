package com.netcom.firebaseexoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PlaybackSpeedSelector extends BottomSheetDialogFragment {
    float speed = 1f;
    PlaybackParameters parameters;
    TextView playback_speed ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_playback_speed_selector , container , false);
        RadioGroup radioGroup = v.findViewById(R.id.video_speed);
        int selectedSpeed = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = v.findViewById(selectedSpeed);

/*
        playback_speed = v.findViewById(R.id.exo_playback_speed);
        playback_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R.id.zero_point5 == radioButton.getId()){
                    playback_speed.setText("0.5x");
                }else if (R.id.zero_point75 == radioButton.getId()){
                    playback_speed.setText("0.75x");
                }else if (R.id.normal == radioButton.getId()){
                    playback_speed.setText("1x");
                }else if (R.id.one_point25 == radioButton.getId()){
                    playback_speed.setText("1.25x");
                }else if (R.id.zero_point5 == radioButton.getId()){
                    playback_speed.setText("1.5x");
                }
            }
        });
*/

/*
        Intent sp = new Intent(v.getContext() , NewPlayer.class);
        parameters = new PlaybackParameters(speed);

//        Toast.makeText(getActivity() , radioButton.getText() , Toast.LENGTH_SHORT).show();
        if (R.id.zero_point5 == radioButton.getId()){
            sp.putExtra("SPEED" , 0.5F);
        }else if (R.id.zero_point75 == radioButton.getId()){
            sp.putExtra("SPEED" , 0.75f);
        }else if (R.id.normal == radioButton.getId()){
            sp.putExtra("SPEED" , 1.0f);
        }else if (R.id.one_point25 == radioButton.getId()){
            sp.putExtra("SPEED" , 1.25f);
        }else if (R.id.zero_point5 == radioButton.getId()){
            sp.putExtra("SPEED" , 1.5f);
        }

        v.getContext().startActivity(sp);
*/
        return v;
    }
}