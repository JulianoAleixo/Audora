package service;

import model.Genre;
import repository.GenreRepository;

import java.util.List;
import java.util.Optional;

public class GenreService {
    private final GenreRepository repository = new GenreRepository();

    public List<Genre> getList() {
        return repository.getAll();
    }

    public Genre create(Genre genre) {
        return repository.create(genre);
    }

    public Optional<Genre> get(String id) {
        return repository.getById(id);
    }

    public boolean update(Genre genre) {
        return repository.update(genre);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}
