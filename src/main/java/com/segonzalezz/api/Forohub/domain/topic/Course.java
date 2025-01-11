package com.segonzalezz.api.Forohub.domain.topic;

import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseDataUpdate;
import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseRegistrationData;
import com.segonzalezz.api.Forohub.domain.user.DTO.UserRegistrationData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name= "courses")
@Entity(name= "Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    private Boolean active;

    public Course(@Valid CourseRegistrationData courseRegistrationData) {
        this.active = true;
        this.name =  courseRegistrationData.name();
        this.category = courseRegistrationData.category();
    }

    public void updateData(CourseDataUpdate courseDataUpdate) {
        if (courseDataUpdate.name() != null) {
            this.name = courseDataUpdate.name();
        }
        if (courseDataUpdate.category() != null) {
            this.category = courseDataUpdate.category();
        }
    }

    public void turnOffActive() {
        this.active = false;
    }
}
