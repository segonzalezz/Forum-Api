package com.segonzalezz.api.Forohub.domain.topic.DTO;

import com.segonzalezz.api.Forohub.domain.topic.Course;
import com.segonzalezz.api.Forohub.domain.topic.CourseCategory;

public record CourseDataList(Long id, String name, CourseCategory category) {
    public CourseDataList(Course course){
        this(course.getId(),course.getName(), course.getCategory());
    }
}
