package org.martindex.springcloud.ms.courses.repositories;
import org.martindex.springcloud.ms.courses.entities.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    String QUERY_DELETE_COURSE_USER_BY_ID = "delete from {h-schema}courses_users cu where cu.user_id=:userId";

    @Modifying
    @Query(value = QUERY_DELETE_COURSE_USER_BY_ID, nativeQuery = true)
    void deleteCourseUserByUserId(Long userId);

}

