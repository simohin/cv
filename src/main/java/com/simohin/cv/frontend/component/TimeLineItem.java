package com.simohin.cv.frontend.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeLineItem extends HorizontalLayout {

    private final Map<String, String> styles = new HashMap<>() {{
        put("margin", "0");
    }};

    public TimeLineItem(String title, String text) {
        this(null, title, text);
    }


    public TimeLineItem(String imgUrl, String title, String text) {
        var components = new ArrayList<Component>();

        if (imgUrl != null) {
            components.add(new Avatar() {{
                this.setImage(imgUrl);
                addThemeVariants(AvatarVariant.LUMO_XLARGE);
            }});
        }

        if (title == null || text == null) {
            throw new IllegalArgumentException();
        }

        components.add(new VerticalLayout(new H3(title) {{
            var style = getStyle();
            styles.forEach(style::set);
        }}, new Text(text)));
        add(components.toArray(new Component[]{}));
    }
}
