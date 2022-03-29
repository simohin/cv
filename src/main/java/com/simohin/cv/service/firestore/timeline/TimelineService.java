package com.simohin.cv.service.firestore.timeline;

import com.simohin.cv.model.tmieline.TimelineItem;

import java.util.Collection;

public interface TimelineService {
    Collection<TimelineItem> getAll();
}
