package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component(CalendarView.COMPONENT_NAME)
@UIScope
public class CalendarView extends VerticalLayout implements View {

    protected static final String COMPONENT_NAME = "Calendar";

    public CalendarView() {
        setSizeFull();
        add(getCalendlyIframe());
    }

    private IFrame getCalendlyIframe() {
        return new IFrame() {{
            setPadding(false);
            setMargin(false);
            getStyle().set("overflow", "hidden");
            getElement().setAttribute("frameborder", "0").setAttribute("scrolling", "no");
            setSrc("https://calendly.com/simohin_timofei/30min?embed_type=Inline&hide_gdpr_banner=1");
            setSizeFull();
        }};
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }
}
