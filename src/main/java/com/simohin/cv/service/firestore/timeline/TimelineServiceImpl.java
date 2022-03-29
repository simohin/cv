package com.simohin.cv.service.firestore.timeline;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.simohin.cv.model.tmieline.TimelineItem;
import com.simohin.cv.service.firestore.FirestoreService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SpringComponent
@Slf4j
@Setter
public class TimelineServiceImpl implements FirestoreService, TimelineService {

    private static final String COLLECTION_NAME = "timeline";
    private static final String DOCUMENT_NAME = "items";
    private Firestore firestore;
    private CollectionReference collection;
    private DocumentReference document;

    @Override
    public String getDocument() {
        return DOCUMENT_NAME;
    }

    @Override
    public String getCollection() {
        return COLLECTION_NAME;
    }

    @Cacheable("timeline-all")
    public Collection<TimelineItem> getAll() {
        try {
            return collection.get().get().getDocuments().stream()
                    .map(this::parseItem)
                    .collect(Collectors.toSet());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to get timeline data", e);
            return new HashSet<>();
        }
    }

    private TimelineItem parseItem(QueryDocumentSnapshot snapshot) {
        return snapshot.toObject(TimelineItem.class);
    }
}
