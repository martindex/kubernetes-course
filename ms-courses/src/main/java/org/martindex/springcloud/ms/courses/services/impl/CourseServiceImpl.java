package org.martindex.springcloud.ms.courses.services.impl;

import org.martindex.springcloud.ms.courses.clients.dtos.UserRemoteDto;
import org.martindex.springcloud.ms.courses.clients.remotes.UserRemote;
import org.martindex.springcloud.ms.courses.clients.rests.UserRemoteClientRest;
import org.martindex.springcloud.ms.courses.dtos.CourseDto;
import org.martindex.springcloud.ms.courses.entities.Course;
import org.martindex.springcloud.ms.courses.entities.CourseUser;
import org.martindex.springcloud.ms.courses.repositories.CourseRepository;
import org.martindex.springcloud.ms.courses.services.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    private final UserRemoteClientRest userRemoteClientRest;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper, UserRemoteClientRest userRemoteClientRest) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.userRemoteClientRest = userRemoteClientRest;
    }

    @Override
    public List<CourseDto> getList() {
        return ((List<Course>)courseRepository.findAll())
                .stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> getById(Long id) {
        return courseRepository.findById(id)
                .map(value -> modelMapper.map(value, CourseDto.class));
    }

    @Override
    public CourseDto save(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<UserRemoteDto> assignUser(UserRemoteDto userRemoteDto, Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    UserRemote userRemote = userRemoteClientRest.getUserById(userRemoteDto.getId());
                    CourseUser courseUser = new CourseUser();
                    courseUser.setUserId(userRemote.getId());
                    course.addCourseUser(courseUser);
                    courseRepository.save(course);
                    return modelMapper.map(userRemote, UserRemoteDto.class);
                });
    }

    @Override
    @Transactional
    public Optional<UserRemoteDto> createUser(UserRemoteDto userRemoteDto, Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    UserRemote userRemote = userRemoteClientRest.createUser(modelMapper.map(userRemoteDto, UserRemote.class));
                    CourseUser courseUser = new CourseUser();
                    courseUser.setUserId(userRemote.getId());
                    course.addCourseUser(courseUser);
                    courseRepository.save(course);
                    return modelMapper.map(userRemote, UserRemoteDto.class);
                });
    }

    @Override
    public Optional<UserRemoteDto> deallocateUser(UserRemoteDto userRemoteDto, Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    UserRemote userRemote = userRemoteClientRest.getUserById(userRemoteDto.getId());
                    CourseUser courseUser = new CourseUser();
                    courseUser.setUserId(userRemote.getId());
                    course.removeCourseUser(courseUser);
                    courseRepository.save(course);
                    return modelMapper.map(userRemote, UserRemoteDto.class);
                });
    }


}
