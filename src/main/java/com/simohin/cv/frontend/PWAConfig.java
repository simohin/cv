package com.simohin.cv.frontend;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import org.springframework.stereotype.Component;

@Component
@PWA(name = PWAConfig.NAME,
        shortName = PWAConfig.SHORT_NAME,
        iconPath = "/images/icon.png",
        startPath = ""
)
@Push
public class PWAConfig implements AppShellConfigurator {

    protected static final String NAME = "Simohin Timofei personal website";
    protected static final String SHORT_NAME = "Simohin Timofey";
}
