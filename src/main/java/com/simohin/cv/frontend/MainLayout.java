package com.simohin.cv.frontend;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
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
    private final H3 title;
    private final Tabs tabs;
    private final Div placeholder;
    private final Map<String, View> nameToView;

    public MainLayout(Collection<View> views) {
        nameToView = views.stream().collect(Collectors.toMap(View::getName, it -> it));
        title = getTitle();
        tabs = getTabs();
        placeholder = getPlaceholder();
        addToNavbar(getWrapper());
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
                    .forEach(this::add);
            getStyle().set("flex-grow", String.valueOf(TABS_FLEX_GROW));
            addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
            addSelectedChangeListener(MainLayout.this::setSelectedTab);
        }};
    }


    private void setSelectedTab(Tabs.SelectedChangeEvent e) {
        var component = Optional.ofNullable(nameToView.get(e.getSelectedTab().getLabel()))
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
        return new HorizontalLayout(title, placeholder, tabs) {{
            setWidthFull();
            setJustifyContentMode(JustifyContentMode.BETWEEN);
        }};
    }
}
