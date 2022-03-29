package com.simohin.cv.model.meeting;

import java.time.LocalDateTime;

public record SetMeetingRequest(
        String title,
        LocalDateTime start,
        LocalDateTime end,
        String description
) {
}
