package com.manning.sbip.ch07.controller;

import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses/")
@Tag(name = "Course Controller", description = "This REST controller provide services to manage courses in the Course Tracker application")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all courses available")
    public Iterable<Course> getAllCourses() {
        return courseService.getCourses();
    }

    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides course detail by id")
    public Optional<Course> getCourseById(@PathVariable("id") long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("category/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides course detail by category")
    public Iterable<Course> getCourseByCategory(@PathVariable("name") String category) {
        return courseService.getCoursesByCategory(category);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new course")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates the course details by id")
    public Course updateCourse(@PathVariable("id") long courseId, @RequestBody Course course) {
        return courseService.updateCourse(courseId, course);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes the course detail by id")
    void deleteCourseById(@PathVariable("id") long courseId) {
        courseService.deleteCourseById(courseId);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes all courses")
    void deleteCourses() {
        courseService.deleteCourses();
    }
}
