package com.example.deepa.musicplayer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    ArrayList<Song> songs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songs=new ArrayList<Song>();
        songs.add(new Song("Iru Pookal","A.R.R",R.raw.irupookal));
        songs.add(new Song("Maargazhi Poove","Gifted",R.raw.maargazhipoove));
        songs.add(new Song("Odakaara","Indra",R.raw.odakaara));
        songs.add(new Song("Poongatrille","dilSe",R.raw.poongaatrilae));
        songs.add(new Song("Senyore Senyore","Deva",R.raw.senyoresenyore));
        songs.add(new Song("Soniya Soniya","A.R.R",R.raw.soniyasoniya));
        songs.add(new Song("Thaiyya Thaiyya","A.R.R",R.raw.thaiyyathaiyya));
        songs.add(new Song("Urvasi Urvasi","kadhalan",R.raw.urvasiurvasi));
        songs.add(new Song("Varaaganadhi","sangamam",R.raw.varaaganadhi));
        songs.add(new Song("Vidukadhaiya","A.R.R",R.raw.vidukadhaiyaaindha));
        SongAdapter adapter = new SongAdapter(this,songs);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getBaseContext(), PlayActivity.class);
                intent.putExtra("SongReference", (songs.get(i)).getReferenceId());
                intent.putExtra("SongName", (songs.get(i)).getName());
                startActivity(intent);
            }
        });
    }
}