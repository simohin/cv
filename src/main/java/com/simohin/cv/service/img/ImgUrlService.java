package com.simohin.cv.service.img;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@SpringComponent
@Slf4j
public class ImgUrlService {

    private static final String COLLECTION_NAME = "images";
    private static final String DOCUMENT_NAME = "url";
    private final Firestore firestore;
    private final CollectionReference collection;
    private final DocumentReference document;

    public ImgUrlService(Firestore firestore) {
        this.firestore = firestore;
        collection = this.firestore.collection(COLLECTION_NAME);
        document = collection.document(DOCUMENT_NAME);
    }

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
}
