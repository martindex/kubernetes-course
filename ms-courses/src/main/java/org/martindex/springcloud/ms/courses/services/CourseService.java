package org.martindex.springcloud.ms.courses.services;

import org.martindex.springcloud.ms.courses.dtos.CourseDto;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseDto> getList();
    Optional<CourseDto> getById(Long id);
    CourseDto save(CourseDto courseDto);
    void delete(Long id);
}

