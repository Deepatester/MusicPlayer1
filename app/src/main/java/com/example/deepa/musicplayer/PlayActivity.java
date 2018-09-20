package com.example.deepa.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private Thread thread=null;
    private AudioManager mAudiomanager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChange;
    private int songReference;
    private String name="Song Name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent=getIntent();
        songReference=intent.getIntExtra("SongReference",R.raw.irupookal);
        name=intent.getStringExtra("SongName");
        TextView SongName =((TextView)findViewById(R.id.song_name));
        SongName.setText(name);
        mAudiomanager=(AudioManager)getSystemService(AUDIO_SERVICE);
        mAudioFocusChange=new AudioManager.OnAudioFocusChangeListener()
        {
            @Override
            public void onAudioFocusChange(int state) {
                if(state==AudioManager.AUDIOFOCUS_GAIN)
                {
                    startSong();
                }
                else if(state==AudioManager.AUDIOFOCUS_LOSS)
                {
                    mMediaPlayer.pause();
                }
                else if(state==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
                {
                    mMediaPlayer.pause();
                }
            }
        };
              //add onclick listener to the play button
         ImageView Play = (ImageView)findViewById(R.id.play);
         Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(thread==null) {
                    startSong();
                }
            }
        });
        ImageView Pause = (ImageView)findViewById(R.id.pause);
        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediaPlayer.pause();
                          }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMemory();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayer.pause();
    }

    @Override
    protected void onStart() {
        int state=mAudiomanager.requestAudioFocus(mAudioFocusChange,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
        if(state==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            super.onStart();
            startSong();
        }
        else
        {
            Toast.makeText(PlayActivity.this,"Can not gain audio focus",Toast.LENGTH_SHORT).show();
        }
    }

    private void startSong()
    {
        if(mMediaPlayer==null) {
            mMediaPlayer=MediaPlayer.create(this,songReference);
        }
        mMediaPlayer.start();

    }

    synchronized private void releaseMemory()
    {
        mMediaPlayer.pause();
        mMediaPlayer.release(); //problem is occuring
        mMediaPlayer=null;
        mAudiomanager.abandonAudioFocus(mAudioFocusChange);
    }

    public void backToHome(View view){
        Intent myIntent1 = new Intent(PlayActivity.this,MainActivity.class);
        startActivity(myIntent1);
    }
}