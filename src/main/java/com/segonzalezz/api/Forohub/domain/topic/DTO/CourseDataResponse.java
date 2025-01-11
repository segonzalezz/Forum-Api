package com.segonzalezz.api.Forohub.domain.topic.DTO;

import com.segonzalezz.api.Forohub.domain.topic.CourseCategory;

public record CourseDataResponse(
        Long id,
        String name,
        CourseCategory category
) {
}
