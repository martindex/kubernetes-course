package org.martindex.springcloud.ms.users.repositories;

import java.util.Optional;
import org.martindex.springcloud.ms.users.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}

