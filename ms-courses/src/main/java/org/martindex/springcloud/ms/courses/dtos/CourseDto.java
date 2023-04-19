package org.martindex.springcloud.ms.courses.dtos;

import java.util.List;
import org.martindex.springcloud.ms.courses.clients.dtos.UserRemoteDto;

public class CourseDto {

    private Long id;

    private String name;

    private List<CourseUserDto> courseUserList;

    private List<UserRemoteDto> userRemoteList;

    public CourseDto() {}

    public CourseDto(Long id, String name, List<CourseUserDto> courseUserList, List<UserRemoteDto> userRemoteList) {
        this.id = id;
        this.name = name;
        this.courseUserList = courseUserList;
        this.userRemoteList = userRemoteList;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseUserDto> getCourseUserList() {
        return courseUserList;
    }

    public void setCourseUserList(List<CourseUserDto> courseUserList) {
        this.courseUserList = courseUserList;
    }

    public List<UserRemoteDto> getUserRemoteList() {
        return userRemoteList;
    }

    public void setUserRemoteList(List<UserRemoteDto> userRemoteList) {
        this.userRemoteList = userRemoteList;
    }
}
