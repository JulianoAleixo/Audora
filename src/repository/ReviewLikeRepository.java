package repository;

import database.ConnectionFactory;
import model.Follow;
import model.ReviewLike;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewLikeRepository {

    public ReviewLike create(ReviewLike review_like) {
        String sql = "INSERT INTO review_likes (reviews, users) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, review_like.getReviews());
            stmt.setString(2, review_like.getUsers());
            stmt.executeUpdate();
            return review_like;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ReviewLike> getByUser(String id) {
        List<ReviewLike> review_likes = new ArrayList<>();
        String sql = "SELECT * FROM review_likes WHERE users = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                review_likes.add(new ReviewLike(
                        rs.getString("reviews"),
                        rs.getString("users")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review_likes;
    }

    public List<ReviewLike> getAll() {
        List<ReviewLike> review_likes = new ArrayList<>();
        String sql = "SELECT * FROM review_likes";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                review_likes.add(new ReviewLike(
                        rs.getString("reviews"),
                        rs.getString("users")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return review_likes;
    }

    public boolean delete(ReviewLike review_like) {
        String sql = "DELETE FROM review_likes WHERE reviews = ? AND users = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, review_like.getReviews());
            stmt.setString(2, review_like.getUsers());
            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
