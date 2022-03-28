package com.simohin.cv.service.firestore.timeline;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.simohin.cv.service.firestore.FirestoreService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@SpringComponent
@Slf4j
@Setter
public class TimelineService implements FirestoreService {

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
    public Map<String, String> getAll() {
        try {
            return collection.get().get().getDocuments().stream()
                    .map(QueryDocumentSnapshot::getData)
                    .collect(Collectors.toMap(
                            it -> it.get("title").toString(),
                            it -> it.get("text").toString()
                    ));
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to get timeline data", e);
            return new HashMap<>();
        }
    }
}
