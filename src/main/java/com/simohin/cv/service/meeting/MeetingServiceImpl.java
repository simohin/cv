package com.simohin.cv.service.meeting;

import com.simohin.cv.model.meeting.SetMeetingRequest;
import com.simohin.cv.model.meeting.SetMeetingResult;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@SpringComponent
@Slf4j
public class MeetingServiceImpl implements MeetingService {
    private final Random r = new Random();

    @Override
    public SetMeetingResult set(SetMeetingRequest request) {
        log.info("Meeting request: {}", request);

        return r.nextBoolean()
                ? new SetMeetingResult()
                : new SetMeetingResult(false, request.start().plusMinutes(30));
    }
}
