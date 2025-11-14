package model;

import java.util.UUID;

public class ReviewLike {
    private String reviews;
    private String users;

    public ReviewLike() {}

    public ReviewLike(String reviews, String users) {
        this.reviews = reviews;
        this.users = users;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
