package com.netcom.firebaseexoplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.video.VideoListener;

import org.json.JSONArray;

public class NewPlayer extends AppCompatActivity {

    boolean isFullScreen = false;

    ImageView play, pause, prev, forw;
    ProgressBar progressBar;
    SimpleExoPlayer simpleExoPlayer;
    PlayerView playerView;
    AspectRatioFrameLayout aspectRatioFrameLayout;
    float speed = 1f;
    String link;
    int FLAG = 1;
    Button takeTest;
    ImageView fullScreen, video_quality, settings, bookmark;
    TextView playback_speed;

//    TextView vid_quality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
//for action
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        aspectRatioFrameLayout = findViewById(R.id.asp_lay);
        aspectRatioFrameLayout.setAspectRatio(16f / 9f);

// Set Secure Screen:
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        //initializing
        takeTest = findViewById(R.id.takeTestId);
        playerView = findViewById(R.id.exo_player_view);
        playback_speed = findViewById(R.id.exo_playback_speed);
        play = findViewById(R.id.exo_play);
        pause = findViewById(R.id.exo_pause);
        prev = findViewById(R.id.exo_prev);
        forw = findViewById(R.id.exo_ffwd);
        progressBar = findViewById(R.id.progressBar);
        video_quality = findViewById(R.id.exo_track_selection_view);

        //hide controls:
        playerView.hideController();

        //Bookmark:
        bookmark = findViewById(R.id.exo_bookmark);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FLAG == 1) {
                    bookmark.setBackgroundResource(R.drawable.ic_bookmark_off);
                    FLAG = 2;
                    Toast.makeText(getApplicationContext(), "Bookmarked", Toast.LENGTH_SHORT).show();
                } else if (FLAG == 2) {
                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    Toast.makeText(getApplicationContext(), "Bookmark Removed", Toast.LENGTH_SHORT).show();
                    FLAG = 1;
                }
            }
        });

        //TakeTest
        takeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewPlayer.this, TestPanel.class);
                startActivity(i);
            }
        });



        //Settings
        settings = findViewById(R.id.exo_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsClass settingsClass = new SettingsClass();
                settingsClass.show(getSupportFragmentManager(), "VideoSettings");
            }
        });

        playback_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaybackSpeedSelector playbackSpeedSelector = new PlaybackSpeedSelector();
                playbackSpeedSelector.show(getSupportFragmentManager(), "VideoSpeed");

            }
        });

        video_quality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VideoQualitySelector videoQualityClass = new VideoQualitySelector();
                videoQualityClass.show(getSupportFragmentManager(), "VideoQuality");
            }
        });

        //end


        fullScreen = findViewById(R.id.exo_fullscreen);
        fullScreen.setBackgroundResource(R.drawable.exo_icon_fullscreen_enter);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(85, 85);
        fullScreen.setLayoutParams(params);
        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (FLAG == 1) {
                    isFullScreen = true;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {           // android 11 .... api 30
                        final WindowInsetsController insetsController = getWindow().getInsetsController();
                        if (insetsController != null) {
                            insetsController.hide(WindowInsets.Type.statusBars());
                        }
                    } else {        // API level < 30
                        getWindow().setFlags(
                                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        );
                    }

                    aspectRatioFrameLayout.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getSupportActionBar().hide();
                    fullScreen.setBackgroundResource(R.drawable.exo_icon_fullscreen_exit);
                    FLAG = 2;

                } else if (FLAG == 2) {
//                    onBackPressedFullBtn(FLAG);
                    isFullScreen = false;
                    // cut copy paste
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    getSupportActionBar().show();
                    aspectRatioFrameLayout.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
                    aspectRatioFrameLayout.setAspectRatio(16f / 9f);
                    fullScreen.setBackgroundResource(R.drawable.exo_icon_fullscreen_enter);

                    FLAG = 1;


                }
            }
        });


        //track selection:
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
        trackSelector.setParameters(trackSelector.buildUponParameters()
//                .setMinVideoSize(260,144)
                        .setMaxVideoSize(280, 144)
                        .setRendererDisabled(C.TRACK_TYPE_TEXT, true)
                        .setMaxVideoSizeSd()
                        .build()
        );
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build();    // .setTrackSelector(defaultTrackSelector) set before build method

        //get link:
        Intent linkIntent = getIntent();
        link = linkIntent.getStringExtra("Video_Link");

        //get speed:
        Intent speedIntent = getIntent();
//        speed = speedIntent.getFloatExtra("SPEED" , 0.5f);
        speed = speedIntent.getFloatExtra("SPEED_FLAG", 1.75f);
    }


    private void initplayer() {

        //Bandwidth
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(this).build();
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
        trackSelector.setParameters(trackSelector.buildUponParameters()
                .setMinVideoSize(250, 144));

        //Normal MediaSource
        MediaItem mediaItem = MediaItem.fromUri(link);
        playerView.setPlayer(simpleExoPlayer);
        simpleExoPlayer.setMediaItem(mediaItem);

        simpleExoPlayer.prepare();
        simpleExoPlayer.pause();
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == simpleExoPlayer.STATE_BUFFERING) {
                    playerView.hideController();
                    playerView.setControllerHideOnTouch(false);
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    playerView.setControllerHideOnTouch(true);
                    progressBar.setVisibility(View.INVISIBLE);
//                    playerView.hideController();
                }
            }
        });

        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == simpleExoPlayer.STATE_READY) {
                    settings.setVisibility(View.VISIBLE);
                    aspectRatioFrameLayout.setAspectRatio(16f / 9f);
                } else {
                    playerView.hideController();
                }
            }
        });

        simpleExoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == simpleExoPlayer.STATE_BUFFERING){
                    // hide controls
                    // show progress
                }else {
                    // hide progress and controls
                }
            }
        });

        simpleExoPlayer.getCurrentWindowIndex();

        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

//                Toast.makeText(getApplicationContext() , "TrackChanged" , Toast.LENGTH_SHORT).show();

            }
        });

        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Toast.makeText(getApplicationContext(), "Could not load files; quota has been exceeded for this project or Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onBackPressed() {
        if (isFullScreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getSupportActionBar().show();
            aspectRatioFrameLayout.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
            aspectRatioFrameLayout.setAspectRatio(16f / 9f);
            fullScreen.setBackgroundResource(R.drawable.exo_icon_fullscreen_enter);
            isFullScreen = false;
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initplayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpleExoPlayer.setPlayWhenReady(false);
        playerView.hideController();
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleExoPlayer.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        simpleExoPlayer.seekToDefaultPosition();
    }

    @Override
    protected void onStop() {
        super.onStop();
        simpleExoPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }



}
