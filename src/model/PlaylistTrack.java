package model;

import java.util.UUID;

public class PlaylistTrack {
    private String playlists;
    private String tracks;

    public PlaylistTrack() {}

    public PlaylistTrack(String playlists, String tracks) {
        this.playlists = playlists;
        this.tracks = tracks;
    }

    public String getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String playlists) {
        this.playlists = playlists;
    }

    public String getTracks() {
        return tracks;
    }

    public void setTracks(String tracks) {
        this.tracks = tracks;
    }
}
