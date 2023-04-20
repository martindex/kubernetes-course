package org.martindex.springcloud.ms.courses.entities;

import java.util.ArrayList;
import java.util.List;
import org.martindex.springcloud.ms.courses.clients.remotes.UserRemote;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<CourseUser> courseUserList;

    @Transient
    private List<UserRemote> userRemoteList;

    public Course(){
        this.courseUserList = new ArrayList<>();
        this.userRemoteList = new ArrayList<>();
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

    public List<CourseUser> getCourseUserList() {
        return courseUserList;
    }

    public void setCourseUserList(List<CourseUser> courseUserList) {
        this.courseUserList = courseUserList;
    }

    public List<UserRemote> getUserRemoteList() {
        return userRemoteList;
    }

    public void setUserRemoteList(List<UserRemote> userRemoteList) {
        this.userRemoteList = userRemoteList;
    }

    public void addCourseUser(CourseUser courseUser) {
        courseUserList.add(courseUser);
    }

    public void removeCourseUser(CourseUser courseUser) {
        courseUserList.remove(courseUser);
    }
}

