package com.netcom.firebaseexoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class VideoList extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        recyclerView = findViewById(R.id.recyclerViewId);
        List<ModelClass> modelClasses = new ArrayList<>();



        modelClasses.add(new ModelClass("Create Custom Button (144p) ", R.drawable.two, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/Custom%20Button_144P.mp4?alt=media&token=663a1e11-a9f4-4f8c-aa36-2c7f9d45f3fa"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (53MB) ", R.drawable.three, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858.mp4?alt=media&token=1025359c-1529-4f63-8968-cdd45dc4982b"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (29MB) ", R.drawable.one, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858_low_size.mp4?alt=media&token=8a7a3e39-663e-4a89-995b-557659f351d8"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (53MB) ", R.drawable.three, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858.mp4?alt=media&token=1025359c-1529-4f63-8968-cdd45dc4982b"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (29MB) ", R.drawable.one, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858_low_size.mp4?alt=media&token=8a7a3e39-663e-4a89-995b-557659f351d8"));
        modelClasses.add(new ModelClass("Create Custom Button (144p) ", R.drawable.two, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/Custom%20Button_144P.mp4?alt=media&token=663a1e11-a9f4-4f8c-aa36-2c7f9d45f3fa"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (53MB) ", R.drawable.three, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858.mp4?alt=media&token=1025359c-1529-4f63-8968-cdd45dc4982b"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (29MB) ", R.drawable.one, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858_low_size.mp4?alt=media&token=8a7a3e39-663e-4a89-995b-557659f351d8"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (53MB) ", R.drawable.three, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858.mp4?alt=media&token=1025359c-1529-4f63-8968-cdd45dc4982b"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (29MB) ", R.drawable.one, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858_low_size.mp4?alt=media&token=8a7a3e39-663e-4a89-995b-557659f351d8"));
        modelClasses.add(new ModelClass("Create Custom Button (144p) ", R.drawable.two, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/Custom%20Button_144P.mp4?alt=media&token=663a1e11-a9f4-4f8c-aa36-2c7f9d45f3fa"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (53MB) ", R.drawable.three, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858.mp4?alt=media&token=1025359c-1529-4f63-8968-cdd45dc4982b"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (29MB) ", R.drawable.one, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858_low_size.mp4?alt=media&token=8a7a3e39-663e-4a89-995b-557659f351d8"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (53MB) ", R.drawable.three, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858.mp4?alt=media&token=1025359c-1529-4f63-8968-cdd45dc4982b"));
        modelClasses.add(new ModelClass("1.1 Introduction to Complex Number (29MB) ", R.drawable.one, "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/MVI_9858_low_size.mp4?alt=media&token=8a7a3e39-663e-4a89-995b-557659f351d8"));

//        modelClasses.add(new ModelClass("Escape Plan" , R.drawable.escape_plan , "https://bit.ly/3oU0uHm"));
//        modelClasses.add(new ModelClass("ManiFest Manually" , R.drawable.escape_plan , "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/PDF%2Fplaylist.m3u8?alt=media&token=bc5165fb-9caa-462b-9293-d2558ca43f6a"));
        Adapter adapter = new Adapter(modelClasses, VideoList.class);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_bookmarkId:
                startActivity(new Intent(getApplicationContext(), Bookmark.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


