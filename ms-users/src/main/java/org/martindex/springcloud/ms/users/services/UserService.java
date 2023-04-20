package org.martindex.springcloud.ms.users.services;

import java.util.List;
import java.util.Optional;
import org.martindex.springcloud.ms.users.dtos.UserDto;

public interface UserService {
    List<UserDto> getList();
    Optional<UserDto> getById(Long id);
    UserDto save(UserDto userDto);
    void delete(Long id);

    List<UserDto> getListByIds(Iterable<Long> ids);

    Optional<UserDto> getByEmail(String email);
}


