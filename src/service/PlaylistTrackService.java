package service;

import model.PlaylistTrack;
import repository.PlaylistTrackRepository;
import java.util.List;

public class PlaylistTrackService {
    private final PlaylistTrackRepository repository = new PlaylistTrackRepository();

    public PlaylistTrack create(PlaylistTrack playlist_track) {
        return repository.create(playlist_track);
    }

    public boolean delete(PlaylistTrack playlist_track) {
        return repository.delete(playlist_track);
    }

    public List<PlaylistTrack> getList() {
        return repository.getAll();
    }

    public List<PlaylistTrack> getPlaylistTracksByUser(String playlistId) {
        return repository.getByPlaylist(playlistId);
    }
}
    