package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(CalendarView.COMPONENT_NAME)
@UIScope
public class CalendarView extends VerticalLayout implements View {

    protected static final String COMPONENT_NAME = "Calendar";

    private final String src;

    public CalendarView(@Value("${view.calendar.calendly.src}") String src) {
        this.src = src;
        setSizeFull();
        add(getCalendlyIframe());
    }

    private IFrame getCalendlyIframe() {
        return new IFrame() {{
            setSpacing(false);
            setPadding(false);
            setMargin(false);
            getStyle().set("overflow", "hidden");
            getElement().setAttribute("frameborder", "0").setAttribute("scrolling", "no");
            setSrc(src);
            setSizeFull();
        }};
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }
}
