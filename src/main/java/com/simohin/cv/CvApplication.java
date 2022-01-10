package com.simohin.cv;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@PWA(name = CvApplication.PWA_NAME,
        shortName = CvApplication.PWA_SHORT_NAME,
        iconPath = "/images/icon.png"
)
@Push
public class CvApplication implements AppShellConfigurator {

    protected static final String PWA_NAME = "Simohin Timofei personal website";
    protected static final String PWA_SHORT_NAME = "Simohin Timofey";

    public static void main(String[] args) {
        SpringApplication.run(CvApplication.class, args);
    }

}
