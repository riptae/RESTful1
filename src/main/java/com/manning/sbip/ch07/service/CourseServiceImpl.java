package com.manning.sbip.ch07.service;

import com.manning.sbip.ch07.exception.CourseNotFoundException;
import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourseById(long courseId) {

        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format("No course with id %s is available", courseId)
                ));
        return courseRepository.findById(courseId);
    }

    @Override
    public Iterable<Course> getCoursesByCategory(String category) {
        return courseRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void updateCourse(long courseId, Course course) {
        Course dbCourse = courseRepository.findById(courseId).
                orElseThrow(() -> new CourseNotFoundException(
                        String.format("No course with id %s is available", courseId)
                ));

        dbCourse.setName(course.getName());
        dbCourse.setCategory(course.getCategory());
        dbCourse.setDescription(course.getDescription());
        dbCourse.setRating(course.getRating());
        courseRepository.save(dbCourse);

    }

    @Override
    public void deleteCourses() {
        courseRepository.deleteAll();
    }

    @Override
    public void deleteCourseById(long courseId) {
        courseRepository.findById(courseId)
                        .orElseThrow(() -> new CourseNotFoundException(
                                String.format("No course with id %s is available", courseId)
                        ));
        courseRepository.deleteById(courseId);
    }


}
