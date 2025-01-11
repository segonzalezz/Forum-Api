package com.segonzalezz.api.Forohub.domain.topic.DTO;

import com.segonzalezz.api.Forohub.domain.topic.CourseCategory;
import jakarta.validation.constraints.NotBlank;

public record CourseDataUpdate(
        @NotBlank
        Long id,
        String name,
        CourseCategory category
) {
}
