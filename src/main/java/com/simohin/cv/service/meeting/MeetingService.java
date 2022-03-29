package com.simohin.cv.service.meeting;

import com.simohin.cv.model.meeting.SetMeetingRequest;
import com.simohin.cv.model.meeting.SetMeetingResult;

public interface MeetingService {
    SetMeetingResult set(SetMeetingRequest request);
}
