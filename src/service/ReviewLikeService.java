package service;

import model.ReviewLike;
import repository.ReviewLikeRepository;
import java.util.List;
import java.util.Optional;

public class ReviewLikeService {
    private final ReviewLikeRepository repository = new ReviewLikeRepository();

    public ReviewLike create(ReviewLike review_like) {
        return repository.create(review_like);
    }

    public boolean delete(ReviewLike review_like) {
        return repository.delete(review_like);
    }

    public List<ReviewLike> getList() {
        return repository.getAll();
    }

    public List<ReviewLike> getReviewLikesByUser(String userId) {
        return repository.getByUser(userId);
    }
}
    