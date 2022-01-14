package com.simohin.cv.frontend.view.meeting;

import com.simohin.cv.frontend.view.View;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringComponent(MeetingView.COMPONENT_NAME)
@UIScope
public class MeetingView extends VerticalLayout implements View {
    protected static final String COMPONENT_NAME = "Meeting";
    private static final String TITLE_FIELD_LABEL = "Title";
    private static final String TITLE_FIELD_PLACEHOLDER = "Add event name";

    private static final String MEETING_START_FIELD_LABEL = "Add event start";
    private static final String MEETING_END_FIELD_LABEL = "Add event end";
    private static final Duration STEP = Duration.ofMinutes(30);
    private static final LocalDateTime INITIAL_MEETING_START = LocalDateTime.now().plusDays(1);
    private static final LocalDateTime INITIAL_MEETING_END = INITIAL_MEETING_START.plus(STEP);
    private static final String DESCRIPTION_FIELD_LABEL = "Description";
    private static final String DESCRIPTION_FIELD_PLACEHOLDER = "Add a few word";
    private static final String TITLE_TEXT = "Please, pick a time to schedule our wonderful meeting";
    private static final String SUBMIT_BUTTON_TEXT = "Submit";

    private final TextField titleField = new TextField(TITLE_FIELD_LABEL, TITLE_FIELD_PLACEHOLDER) {{
        setWidthFull();
    }};
    private final DateTimePicker meetingStartField = new DateTimePicker(MEETING_START_FIELD_LABEL, INITIAL_MEETING_START) {{
        setStep(STEP);
    }};
    private final DateTimePicker meetingEndField = new DateTimePicker(MEETING_END_FIELD_LABEL, INITIAL_MEETING_END) {{
        setStep(STEP);
    }};

    private final TextArea descriptionField = new TextArea(DESCRIPTION_FIELD_LABEL, DESCRIPTION_FIELD_PLACEHOLDER) {{
        setMinHeight(20f, Unit.VH);
    }};

    public MeetingView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.AROUND);
        add(
                new H1(TITLE_TEXT),
                new FormLayout() {{
                    add(titleField, meetingStartField, meetingEndField, descriptionField);
                    setMaxWidth(70f, Unit.VH);
                    setAlignSelf(Alignment.CENTER, this);
                    setColspan(titleField, 2);
                    setColspan(descriptionField, 2);
                    setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("500px", 2));
                }},
                new Button(SUBMIT_BUTTON_TEXT) {{
                    addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                }}
        );
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }
}
