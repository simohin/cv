package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.component.TimeLineItem;
import com.simohin.cv.frontend.component.TimeLineItems;
import com.simohin.cv.frontend.view.View;
import com.simohin.cv.service.img.ImgUrlService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Set;

@Order(Ordered.HIGHEST_PRECEDENCE)
@SpringComponent(MainView.COMPONENT_NAME)
@UIScope
public class MainView extends VerticalLayout implements View {

    @Autowired
    private ImgUrlService imgUrlService;

    public static final String AVATAR_IMAGE_URL = "/images/avatar.jpg";
    protected static final String COMPONENT_NAME = "Main";
    protected static final String CONTENT_TITLE = "Your heartwarming Java/Kotlin developer";
    protected static final String CONTENT_SUBTITLE = "Goal-focused and inspired to make this world better";
    private static final String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @PostConstruct
    public void init() {
        add(getHeroSection(), getTimeLine());
    }

    private VerticalLayout getHeroSection() {
        return new VerticalLayout(getHeroMain(), getHeroFooter()) {{
            setHeight(100f, Unit.VH);
            setWidthFull();
        }};
    }

    private VerticalLayout getHeroMain() {
        return new VerticalLayout(getAvatar(), getContentTitle(), getContentSubtitle()) {{
            setWidthFull();
            setHeight(80f, Unit.PERCENTAGE);
            setAlignItems(Alignment.CENTER);
            setJustifyContentMode(JustifyContentMode.CENTER);
        }};
    }

    private VerticalLayout getHeroFooter() {
        return new VerticalLayout() {{
            setWidthFull();
            setHeight(20f, Unit.PERCENTAGE);
        }};
    }

    private VerticalLayout getTimeLine() {

        return new TimeLineItems(Set.of(new TimeLineItem("Title", TEXT)));
    }

    private com.vaadin.flow.component.Component getAvatar() {
        return new Avatar() {{
            setHeight(30f, Unit.VH);
            setWidth(30f, Unit.VH);
            imgUrlService.getUri("avatar").map(URI::toString).ifPresent(this::setImage);
        }};
    }

    private com.vaadin.flow.component.Component getContentTitle() {
        return new H1(CONTENT_TITLE) {{
            getStyle().set("margin", "0")
                    .set("text-align", "center");
        }};
    }

    private com.vaadin.flow.component.Component getContentSubtitle() {
        return new H4(CONTENT_SUBTITLE) {{
            getStyle().set("margin-top", "0.2em")
                    .set("color", "var(--lumo-secondary-text-color)")
                    .set("text-align", "center");
        }};
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }
}
