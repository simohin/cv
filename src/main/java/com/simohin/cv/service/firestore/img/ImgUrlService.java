package com.simohin.cv.service.firestore.img;

import java.net.URI;
import java.util.Optional;

public interface ImgUrlService {
    Optional<URI> getUri(String code);
}
