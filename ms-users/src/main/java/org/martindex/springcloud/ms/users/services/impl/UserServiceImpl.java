package org.martindex.springcloud.ms.users.services.impl;

import java.util.List;
import java.util.Optional;
import org.martindex.springcloud.ms.users.clients.rest.CourseRemoteClientRest;
import org.martindex.springcloud.ms.users.dtos.UserDto;
import org.martindex.springcloud.ms.users.entities.User;
import org.martindex.springcloud.ms.users.repositories.UserRepository;
import org.martindex.springcloud.ms.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CourseRemoteClientRest courseRemoteClientRest;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CourseRemoteClientRest courseRemoteClientRest) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.courseRemoteClientRest = courseRemoteClientRest;
    }

    @Override
    public List<UserDto> getList() {
        return ((List<User>)userRepository.findAll()).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return userRepository.findById(id)
                .map(u -> modelMapper.map(u, UserDto.class));
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void delete(Long id) {
        courseRemoteClientRest.deleteCourseUser(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getListByIds(Iterable<Long> ids) {
        return ((List<User>)userRepository.findAllById(ids))
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class));
    }
}

