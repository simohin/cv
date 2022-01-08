package com.simohin.cv.frontend.util.message;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

public class MessageAccessor {

    private static final Collection<String> BASE_NAMES = new HashSet<>() {{
        add("messages/title");
    }};

    private static final MessageSource MESSAGE_SOURCE = new ResourceBundleMessageSource() {{
        setBasenames(BASE_NAMES.toArray(new String[]{}));
        setUseCodeAsDefaultMessage(true);
    }};

    public static String getMessage(String code, String defaultMessage, Object... args) {
        return getMessage(code, defaultMessage, Locale.getDefault(), args);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale, Object... args) {
        return MESSAGE_SOURCE.getMessage(code, args, defaultMessage, locale);
    }
}
