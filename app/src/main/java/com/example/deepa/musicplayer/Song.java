package com.example.deepa.musicplayer;

/**
 * Created by deepa on 08/04/2018.
 */
public class Song {
    private String name;
    private int referenceId;
    private String artistName;
    public Song(String name, String artistName, int referenceId) {
        this.name = name;
        this.referenceId = referenceId;
        this.artistName = artistName;
    }
    public String getName() {
        return name;
    }
    public int getReferenceId(){
        return referenceId;
    }
    public String getArtistName() {
        return artistName;
    }
}
