package service;

import model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    public List<User> getList() {
        return users;
    }

    public User create(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public Optional<User> get(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public boolean delete(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}
