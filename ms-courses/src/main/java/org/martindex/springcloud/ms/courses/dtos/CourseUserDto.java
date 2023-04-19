package org.martindex.springcloud.ms.courses.dtos;
public class CourseUserDto {

    private Long id;

    private Long userId;

    public CourseUserDto() {}

    public CourseUserDto(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

