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
}
