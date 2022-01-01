package com.simohin.cv.frontend.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

@Route("")
public class MainView extends AppLayout {

    private static final String TITLE_TEXT = "Тимофей Симохин";
    private static final Map<String, String> TITLE_CUSTOM_STYLES = new HashMap<>() {{
        put("margin", "0.5em");
        put("align-self", "center");
    }};
    private final H3 title = getTitle();
    private final Tabs tabs = getTabs();

    public MainView() {
        addToNavbar(getWrapper());
    }

    private static H3 getTitle() {
        return new H3(TITLE_TEXT) {{
            var style = getStyle();
            TITLE_CUSTOM_STYLES.forEach(style::set);
            setSizeFull();
        }};
    }

    private static Tabs getTabs() {
        return new Tabs() {{
            add(getMainTab());
        }};
    }

    private HorizontalLayout getWrapper() {
        return new HorizontalLayout(title, tabs) {{
            setWidthFull();
            setJustifyContentMode(JustifyContentMode.BETWEEN);
        }};
    }

    private static Tab getMainTab() {
        return new Tab("Главная");
    }
}
