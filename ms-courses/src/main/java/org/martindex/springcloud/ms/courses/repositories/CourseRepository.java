package org.martindex.springcloud.ms.courses.repositories;
import org.martindex.springcloud.ms.courses.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}

