package com.simohin.cv.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Set;

@Configuration
public class GoogleConfig {

    public static final String USER_ID = "user";
    private static final Set<String> SCOPES = Set.of(CalendarScopes.CALENDAR_READONLY);
    private static final String ACCESS_TYPE = "online";
    @Value("${spring.application.name:CvApplication}")
    private String appName;
    @Value("${google.auth.callback.port:8080}")
    private int callbackPort;
    @Value("${google.auth.callback.host:localhost}")
    private String callbackHost;
    @Value("${google.auth.callback.path:/callback}")
    private String callbackPath;

    @Bean
    public NetHttpTransport googleNetHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    public Calendar calendarService(NetHttpTransport googleNetHttpTransport, Credential googleCredential, JsonFactory googleJsonFactory) {

        return new Calendar.Builder(googleNetHttpTransport, googleJsonFactory, googleCredential)
                .setApplicationName(appName)
                .build();
    }

    @Bean
    public Credential googleCredential(GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow, VerificationCodeReceiver googleVerificationCodeReceiver) throws IOException {

        return new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, googleVerificationCodeReceiver).authorize(USER_ID);
    }

    @Bean
    public GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow(NetHttpTransport googleNetHttpTransport, GoogleClientSecrets googleClientSecrets, JsonFactory googleJsonFactory) throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(
                googleNetHttpTransport, googleJsonFactory, googleClientSecrets, SCOPES)
                .setDataStoreFactory(new MemoryDataStoreFactory())
                .setAccessType(ACCESS_TYPE)
                .build();
    }

    @Bean
    public VerificationCodeReceiver googleVerificationCodeReceiver() {
        return new LocalServerReceiver.Builder()
                .setHost(callbackHost)
                .setPort(callbackPort)
                .setCallbackPath(callbackPath)
                .build();
    }

    @Bean
    public GoogleClientSecrets googleClientSecrets(InputStream googleCredentialsInputStream, JsonFactory googleJsonFactory) throws IOException {
        return GoogleClientSecrets.load(googleJsonFactory, new InputStreamReader(googleCredentialsInputStream));
    }

    @Bean
    public InputStream googleCredentialsInputStream(@Value("${google.config.encoded}") String encoded) {
        return new ByteArrayInputStream(Base64.getDecoder().decode(encoded));
    }

    @Bean
    public JsonFactory googleJsonFactory() {
        return GsonFactory.getDefaultInstance();
    }
}
