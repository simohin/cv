package com.simohin.cv.model.meeting;

import java.time.LocalDateTime;

public record SetMeetingResult(
        boolean success,
        LocalDateTime available
) {
    public SetMeetingResult() {
        this(true, null);
    }
}
