package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.MainLayout;
import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.html.H1;
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

    public MainView() {
        add(new H1("Main view"));
    }

    @Override
    public String getName() {
        return COMPONENT_TAME;
    }
}
