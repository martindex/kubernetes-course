package org.martindex.springcloud.ms.courses.services.impl;

import org.martindex.springcloud.ms.courses.dtos.CourseDto;
import org.martindex.springcloud.ms.courses.entities.Course;
import org.martindex.springcloud.ms.courses.repositories.CourseRepository;
import org.martindex.springcloud.ms.courses.services.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
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
}
