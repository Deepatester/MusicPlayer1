package com.example.deepa.musicplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

/**
 * Created by deepa on 08/04/2018.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    private Song song;
    public SongAdapter(Activity context, ArrayList<Song> info)
    {
        super(context,0,info);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        song=(Song)getItem(position);
        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView txt_name=(TextView)convertView.findViewById(R.id.song_name_text_view);
        txt_name.setText(song.getName());
        TextView txt_detail=(TextView)convertView.findViewById(R.id.details_text_view);
        txt_detail.setText(song.getArtistName());
        return convertView;
    }
}

