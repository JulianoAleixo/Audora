package service;

import model.Follow;
import repository.FollowRepository;
import java.util.List;

public class FollowService {
    private final FollowRepository repository = new FollowRepository();

    public boolean create(Follow follow) {
        return repository.create(follow);
    }

    public boolean delete(Follow follow) {
        return repository.delete(follow);
    }

    public List<String> getFollowers(String userId) {
        return repository.getFollowers(userId);
    }

    public List<String> getFollowing(String userId) {
        return repository.getFollowing(userId);
    }
}
    