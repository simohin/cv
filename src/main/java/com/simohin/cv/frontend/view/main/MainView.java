package com.simohin.cv.frontend.view.main;

import com.simohin.cv.frontend.MainLayout;
import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Simohin Timofei | Main")
@Component(MainView.COMPONENT_TAME)
public class MainView extends VerticalLayout implements View {

    protected static final String COMPONENT_TAME = "Main";

    public MainView() {
        add(new H1("Main view"));
    }

    @Override
    public String getName() {
        return COMPONENT_TAME;
    }
}
