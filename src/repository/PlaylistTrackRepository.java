package repository;

import database.ConnectionFactory;
import model.Follow;
import model.PlaylistTrack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaylistTrackRepository {

    public PlaylistTrack create(PlaylistTrack playlist_track) {
        String sql = "INSERT INTO playlist_tracks (playlists, tracks) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, playlist_track.getPlaylists());
            stmt.setString(2, playlist_track.getTracks());
            stmt.executeUpdate();
            return playlist_track;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PlaylistTrack> getByPlaylist(String playlist) {
        List<PlaylistTrack> playlist_tracks = new ArrayList<>();
        String sql = "SELECT * FROM playlist_tracks WHERE playlists = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, playlist);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                playlist_tracks.add(new PlaylistTrack(
                        rs.getString("playlists"),
                        rs.getString("tracks")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlist_tracks;
    }

    public List<PlaylistTrack> getAll() {
        List<PlaylistTrack> playlist_tracks = new ArrayList<>();
        String sql = "SELECT * FROM playlist_tracks";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                playlist_tracks.add(new PlaylistTrack(
                        rs.getString("playlists"),
                        rs.getString("tracks")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlist_tracks;
    }

    public boolean delete(PlaylistTrack playlist_track) {
        String sql = "DELETE FROM playlist_tracks WHERE playlists = ? AND tracks = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, playlist_track.getPlaylists());
            stmt.setString(2, playlist_track.getTracks());
            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
