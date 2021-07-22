package com.netcom.firebaseexoplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class VideoQualitySelector extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_video_quality_selector, container, false);
        RadioGroup radioGroup = v.findViewById(R.id.video_quality);
        int selectedQuality = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = v.findViewById(selectedQuality);
        Toast.makeText(getActivity(), radioButton.getText(), Toast.LENGTH_SHORT).show();

        return v;
    }
}
