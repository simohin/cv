package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@SpringComponent(MainView.COMPONENT_NAME)
@UIScope
public class MainView extends VerticalLayout implements View {

    public static final String AVATAR_IMAGE_URL = "/images/avatar.jpg";
    protected static final String COMPONENT_NAME = "Main";
    protected static final String CONTENT_TITLE = "Your heartwarming Java/Kotlin developer";
    protected static final String CONTENT_SUBTITLE = "Goal-focused and inspired to make this world better";

    public MainView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.AROUND);
        setAlignItems(Alignment.CENTER);
        add(
                new VerticalLayout(getAvatar(), getContentTitle(), getContentSubtitle()) {{
                    setWidthFull();
                    setAlignItems(Alignment.CENTER);
                    setFlexGrow(5f);
                }},
                new VerticalLayout() {{
                    setWidthFull();
                    setFlexGrow(1f);
                }}
        );
    }

    private com.vaadin.flow.component.Component getAvatar() {
        return new Avatar() {{
            setHeight(30f, Unit.VH);
            setWidth(30f, Unit.VH);
            setImage(AVATAR_IMAGE_URL);
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
