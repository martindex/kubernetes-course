package org.martindex.springcloud.ms.users.repositories;

import org.martindex.springcloud.ms.users.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // Aquí podrías agregar métodos personalizados de ser necesario
}

