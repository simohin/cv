package com.simohin.cv.service.firestore.img;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.simohin.cv.service.firestore.FirestoreService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@SpringComponent
@Slf4j
@Setter
public class ImgUrlServiceImpl implements FirestoreService, ImgUrlService {

    private static final String COLLECTION_NAME = "images";
    private static final String DOCUMENT_NAME = "url";
    private Firestore firestore;
    private CollectionReference collection;
    private DocumentReference document;

    @Cacheable(value = "img.uri", key = "#code")
    public Optional<URI> getUri(String code) {
        Optional<String> uri;
        try {
            uri = Optional.ofNullable(document.get().get().get(code, String.class));
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to get img uri", e);
            return Optional.empty();
        }
        return uri.map(URI::create);
    }

    @Override
    public String getDocument() {
        return DOCUMENT_NAME;
    }

    @Override
    public String getCollection() {
        return COLLECTION_NAME;
    }
}
