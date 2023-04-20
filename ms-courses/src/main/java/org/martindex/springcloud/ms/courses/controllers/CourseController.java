package org.martindex.springcloud.ms.courses.controllers;


import java.util.List;
import org.martindex.springcloud.ms.courses.clients.dtos.UserRemoteDto;
import org.martindex.springcloud.ms.courses.dtos.CourseDto;
import org.martindex.springcloud.ms.courses.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getList() {
        return ResponseEntity.ok(courseService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return courseService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.save(courseDto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid CourseDto courseDto) {
        return courseService.getById(courseDto.getId())
                .map(courseService::save)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return courseService.getById(id)
                .map(existingCourse -> {
                    courseService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/assign-user/{courseId}")
    public ResponseEntity<?> assignUserRemote(@RequestBody UserRemoteDto userRemoteDto, @PathVariable Long courseId) {
        return courseService.assignUser(userRemoteDto, courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-user/{courseId}")
    public ResponseEntity<?> createUserRemote(@RequestBody UserRemoteDto userRemoteDto, @PathVariable Long courseId) {
        return courseService.createUser(userRemoteDto, courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/delete-user/{courseId}")
    public ResponseEntity<?> deleteUserRemote(@RequestBody UserRemoteDto userRemoteDto, @PathVariable Long courseId) {
        return courseService.deallocateUser(userRemoteDto, courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

