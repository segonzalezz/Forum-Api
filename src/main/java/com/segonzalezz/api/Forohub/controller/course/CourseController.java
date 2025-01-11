package com.segonzalezz.api.Forohub.controller.course;

import com.segonzalezz.api.Forohub.domain.topic.Course;
import com.segonzalezz.api.Forohub.domain.topic.CourseRepository;
import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseDataList;
import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseDataResponse;
import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseDataUpdate;
import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseRegistrationData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<CourseDataResponse> registerCourse(@RequestBody @Valid CourseRegistrationData courseRegistrationData,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Course course = courseRepository.save(new Course(courseRegistrationData));
        CourseDataResponse courseDataResponse = new CourseDataResponse(
                course.getId(),
                course.getName(),
                course.getCategory()
        );
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(courseDataResponse);
    }

    @GetMapping
    public ResponseEntity<Page<CourseDataList>> listCourses(@PageableDefault(size = 2) Pageable pagination) {
        Page<Course> coursesPage = courseRepository.findByActiveTrue(pagination);
        if (coursesPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Page<CourseDataList> courseDataLists = coursesPage.map(CourseDataList::new);
        return ResponseEntity.ok(courseDataLists);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCourses(@RequestBody @Valid CourseDataUpdate courseDataUpdate) {
        Course course = courseRepository.getReferenceById(courseDataUpdate.id());
        course.updateData(courseDataUpdate);
        return ResponseEntity.ok(new CourseDataResponse(course.getId(), course.getName(), course.getCategory()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourses(@PathVariable Long id) {
        Course course = courseRepository.getReferenceById(id);
        course.turnOffActive();
        return ResponseEntity.noContent().build();
    }
}
