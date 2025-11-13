package model;

public class Follow {
    private String followerId;
    private String followedId;

    public Follow() {}

    public Follow(String followerId, String followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public String getFollowerId() { return followerId; }
    public void setFollowerId(String userId) { this.followerId = userId; }

    public String getFollowedId() { return followedId; }
    public void setFollowedId(String followedId) { this.followedId = followedId; }
}
