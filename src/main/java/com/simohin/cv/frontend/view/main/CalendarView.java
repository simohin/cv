package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component(CalendarView.COMPONENT_NAME)
@UIScope
public class CalendarView extends VerticalLayout implements View {

    protected static final String COMPONENT_NAME = "Calendar";

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }
}
