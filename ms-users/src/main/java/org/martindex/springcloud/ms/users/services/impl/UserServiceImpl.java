package org.martindex.springcloud.ms.users.services.impl;

import java.util.List;
import java.util.Optional;
import org.martindex.springcloud.ms.users.dtos.UserDto;
import org.martindex.springcloud.ms.users.entities.User;
import org.martindex.springcloud.ms.users.repositories.UserRepository;
import org.martindex.springcloud.ms.users.services.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getList() {
        return ((List<User>)userRepository.findAll()).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return userRepository.findById(id).map(u -> modelMapper.map(u, UserDto.class));
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

