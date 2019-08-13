package net.orekyuu.springbootkata.infrastructure.inmemory;

import net.orekyuu.springbootkata.domain.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository {
    private static ConcurrentHashMap<Long, User> storage = new ConcurrentHashMap<>();

    public void save(User user) {
        if (user.getId() == null) {
            user = new User(nextId(), user.getName());
        }
        storage.put(user.getId(), user);
    }

    public void remove(Long id) {
        storage.remove(id);
    }

    public void removeAll() {
        storage.clear();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    private long nextId() {
        long max = storage.values().stream().mapToLong(User::getId).max().orElse(0);
        return max + 1;
    }
}
