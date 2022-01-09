package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.MainLayout;
import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Route(value = "", layout = MainLayout.class)
@PageTitle(MainView.TITLE)
@Component(MainView.COMPONENT_TAME)
@UIScope
public class MainView extends VerticalLayout implements View {

    protected static final String COMPONENT_TAME = "Main";
    protected static final String TITLE = "Simohin Timofei | Main";
    protected static final String CONTENT_TITLE = "Your heartwarming Java/Kotlin developer";
    protected static final String CONTENT_SUBTITLE = "Goal-focused and inspired to make this world better";
    public static final String AVATAR_IMAGE_URL = "/images/avatar.jpg";

    public MainView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.AROUND);
        setAlignItems(Alignment.CENTER);
        add(
                new VerticalLayout(getAvatar(), getContentTitle(), getContentSubtitle()) {{
                    setWidthFull();
                    setHeight(0.8f, Unit.VMAX);
                    setAlignItems(Alignment.CENTER);
                }},
                new VerticalLayout() {{
                    setWidthFull();
                    setHeight(0.2f, Unit.VMAX);
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
            getStyle().set("margin", "0");
        }};
    }

    private com.vaadin.flow.component.Component getContentSubtitle() {
        return new H4(CONTENT_SUBTITLE) {{
            getStyle().set("margin-top", "0.2em")
                    .set("color", "var(--lumo-secondary-text-color)");
        }};
    }

    @Override
    public String getName() {
        return COMPONENT_TAME;
    }
}
