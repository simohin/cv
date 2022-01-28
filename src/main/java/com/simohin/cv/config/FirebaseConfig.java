package com.simohin.cv.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseOptions firebaseOptions(@Value("${firebase.config.encoded}") String encodedConfig) throws IOException {
        var inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(encodedConfig));
        var credentials = GoogleCredentials.fromStream(inputStream);
        return FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
    }

    @Bean
    public FirebaseApp firebaseApp(FirebaseOptions options, @Value("${firebase.app.name:cv}") String appName) {
        return FirebaseApp.initializeApp(options, appName);
    }

    @Bean
    public Firestore firestore(FirebaseApp app) {
        return FirestoreClient.getFirestore(app);
    }

}
