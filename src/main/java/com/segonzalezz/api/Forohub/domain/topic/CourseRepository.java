package com.segonzalezz.api.Forohub.domain.topic;

import com.segonzalezz.api.Forohub.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByActiveTrue(Pageable pagination);
}
