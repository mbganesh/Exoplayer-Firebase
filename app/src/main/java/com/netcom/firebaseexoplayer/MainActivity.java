package com.netcom.firebaseexoplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;

import javax.sql.DataSource;

public class MainActivity extends AppCompatActivity {


    PlayerView playerView ;
    SimpleExoPlayer player ;
    //String VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/sample.mp4?alt=media&token=bf89697c-c417-45ec-b993-16e32054032c";
    String VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/exo-player-b8500.appspot.com/o/Montessori.mp4?alt=media&token=524d46fc-d0cd-4565-9f48-7b391f1aa697";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        progressBar = findViewById(R.id.progress_bar);
        //btFullScreen = playerView.findViewById(R.id.bt_fullscreen);

        playerView = findViewById(R.id.exo_player_video_id);
//        progressBar.setVisibility(View.GONE);


/*
        //AndroidCoder:
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LoadControl loadControl = new DefaultLoadControl();
        player = new SimpleExoPlayer.Builder(MainActivity.this)
                .build();
        playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(VIDEO_URL));
        player.addMediaItem(mediaItem);

        playerView.setKeepScreenOn(true);
        player.setPlayWhenReady(true);
        player.prepare();
        player.play();


 */

        //AndroidJLelse

        player = new SimpleExoPlayer.Builder(MainActivity.this).build();
        playerView.setPlayer(player);
        player.seekTo(10000);
        player.setPlayWhenReady(true);
//        player.addListener();


      //  initializePlayer();
    }

    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(MainActivity.this)
                .build();
        playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(VIDEO_URL));
        player.addMediaItem(mediaItem);


        player.prepare();
        player.play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
        player.getPlaybackState();


    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}


/*
app:controller_layout_id="@layout/custom_controller_id"
app:player_layout_id="@layout/exo_player_control_view"
 */