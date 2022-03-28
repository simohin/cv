package com.simohin.cv.service.firestore;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;

public interface FirestoreService {

    @Autowired
    default void autowire(Firestore firestore) {
        setFirestore(firestore);
        var collection = firestore.collection(getCollection());
        var document = collection.document(getDocument());
        setCollection(collection);
        setDocument(document);
    }

    String getDocument();

    String getCollection();

    void setFirestore(Firestore fs);

    void setCollection(CollectionReference ref);

    void setDocument(DocumentReference ref);
}
