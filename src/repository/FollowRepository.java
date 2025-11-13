package repository;

import database.ConnectionFactory;
import model.Follow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowRepository {

    public boolean create(Follow follow) {
        String sql = "INSERT INTO Follows (follower_id, followed_id) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, follow.getFollowerId());
            stmt.setString(2, follow.getFollowedId());
            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Follow follow) {
        String sql = "DELETE FROM Follows WHERE follower_id = ? AND followed_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, follow.getFollowerId());
            stmt.setString(2, follow.getFollowedId());
            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getFollowers(String userId) {
        List<String> followers = new ArrayList<>();
        String sql = "SELECT follower_id FROM Follows WHERE followed_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                followers.add(rs.getString("follower_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return followers;
    }

    public List<String> getFollowing(String userId) {
        List<String> following = new ArrayList<>();
        String sql = "SELECT followed_id FROM Follows WHERE follower_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                following.add(rs.getString("followed_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return following;
    }
}
