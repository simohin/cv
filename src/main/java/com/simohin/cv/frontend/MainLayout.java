package com.simohin.cv.frontend;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@UIScope
public class MainLayout extends com.vaadin.flow.component.applayout.AppLayout {

    private static final String TITLE_TEXT = "Тимофей Симохин";
    private static final Map<String, String> TITLE_CUSTOM_STYLES = new HashMap<>() {{
        put("margin", "0.5em");
        put("align-self", "center");
    }};
    private final H3 title;
    private final Tabs tabs;

    public MainLayout(Collection<View> views) {
        title = getTitle();
        tabs = getTabs(views);
        addToNavbar(getWrapper());
    }

    private H3 getTitle() {
        return new H3(TITLE_TEXT) {{
            var style = getStyle();
            TITLE_CUSTOM_STYLES.forEach(style::set);
            setSizeFull();
        }};
    }

    private Tabs getTabs(Collection<View> views) {
        Tabs tabs = new Tabs();
        views.stream()
                .filter(it -> it instanceof com.vaadin.flow.component.Component)
                .map(com.vaadin.flow.component.Component.class::cast)
                .map(view -> {
                    var name = ((View) view).getName();
                    var viewClass = view.getClass();
                    var link = new RouterLink();
                    link.setText(name);
                    link.setClassName(viewClass.getName());
                    return new Tab(link);
                })
                .forEach(tabs::add);
        return tabs;
    }

    private HorizontalLayout getWrapper() {
        return new HorizontalLayout(title, tabs) {{
            setWidthFull();
            setJustifyContentMode(JustifyContentMode.BETWEEN);
        }};
    }
}
