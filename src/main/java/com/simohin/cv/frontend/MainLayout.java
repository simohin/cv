package com.simohin.cv.frontend;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Route("")
@UIScope
public class MainLayout extends com.vaadin.flow.component.applayout.AppLayout {

    private static final String TITLE_TEXT = "Simohin Timofey";
    private static final Map<String, String> TITLE_CUSTOM_STYLES = new HashMap<>() {{
        put("margin", "0.5em");
        put("align-self", "center");
        put("flex-grow", String.valueOf(LOGO_FLEX_GROW));
    }};
    public static final float PLACEHOLDER_FLEX_GROW = 7;
    public static final float TABS_FLEX_GROW = 1;
    public static final float LOGO_FLEX_GROW = 1;
    public static final String DEFAULT_TAB = "Main";
    private final H3 title;
    private final Tabs tabs;
    private final Div placeholder;
    private final Map<String, View> nameToView = new LinkedHashMap<>();

    public MainLayout(List<View> views) {
        views.forEach(it -> nameToView.putIfAbsent(it.getName(), it));
        title = getTitle();
        tabs = getTabs();
        placeholder = getPlaceholder();
        addToNavbar(getWrapper());
        setDrawerOpened(false);
        addToDrawer(tabs);
    }

    private H3 getTitle() {
        return new H3(TITLE_TEXT) {{
            var style = getStyle();
            TITLE_CUSTOM_STYLES.forEach(style::set);
        }};
    }

    private Tabs getTabs() {
        return new Tabs() {{
            nameToView.keySet().stream()
                    .map(Tab::new)
                    .peek(it -> {
                        if (it.getLabel().equalsIgnoreCase(DEFAULT_TAB)) {
                            it.setSelected(true);
                        }
                    })
                    .forEach(this::add);
            selectTab(getSelectedTab());
            setOrientation(Orientation.VERTICAL);
            addSelectedChangeListener(MainLayout.this::handleSelectedChanged);
        }};
    }


    private void handleSelectedChanged(Tabs.SelectedChangeEvent e) {
        selectTab(e.getSelectedTab());
    }

    private void selectTab(Tab tab) {
        var component = Optional.ofNullable(nameToView.get(tab.getLabel()))
                .map(com.vaadin.flow.component.Component.class::cast)
                .orElseThrow();
        setContent(component);
    }

    private Div getPlaceholder() {
        return new Div() {{
            getStyle().set("flex-grow", String.valueOf(PLACEHOLDER_FLEX_GROW));
        }};
    }

    private HorizontalLayout getWrapper() {
        return new HorizontalLayout(title, placeholder, new DrawerToggle()) {{
            setWidthFull();
            setJustifyContentMode(JustifyContentMode.BETWEEN);
        }};
    }
}
