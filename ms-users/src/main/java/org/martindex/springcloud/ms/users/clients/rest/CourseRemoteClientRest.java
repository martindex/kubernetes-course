package org.martindex.springcloud.ms.users.clients.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-courses", url="localhost:8002")
public interface CourseRemoteClientRest {

    @DeleteMapping("/delete-user/{userId}")
    void deleteCourseUser(@PathVariable Long userId);

}
