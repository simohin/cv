package com.simohin.cv.frontend.view.meeting;

import com.simohin.cv.frontend.view.View;
import com.simohin.cv.model.meeting.SetMeetingRequest;
import com.simohin.cv.model.meeting.SetMeetingResult;
import com.simohin.cv.service.meeting.MeetingService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;

@SpringComponent(MeetingView.COMPONENT_NAME)
@UIScope
@RequiredArgsConstructor
@Slf4j
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
    private static final String DESCRIPTION_FIELD_PLACEHOLDER = "Add a few words";
    private static final String TITLE_TEXT = "Please, pick a time to schedule our wonderful meeting";
    private static final String SUBMIT_BUTTON_TEXT = "Submit";
    public static final String UNABLE_SET_MEETING_TEXT = "Unable to set meeting, closest available time is set in form";
    public static final String MEETING_IS_SCHEDULED = "Meeting is scheduled";
    public static final int NOTIFICATION_DURATION = 5_000;

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

    private final FormLayout form = buildForm();

    private final MeetingService meetingService;
    private final H1 title = buildTitle();
    private final Button submitButton = buildSubmitButton();

    @PostConstruct
    public void init() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(title, form, submitButton);
    }

    private Button buildSubmitButton() {
        var button = new Button(SUBMIT_BUTTON_TEXT);
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(this::onFormSubmit);

        return button;
    }

    private void onFormSubmit(ClickEvent<Button> buttonClickEvent) {
        handleSetMeetingResult(meetingService.set(
                new SetMeetingRequest(
                        titleField.getValue(),
                        meetingStartField.getValue(),
                        meetingEndField.getValue(),
                        descriptionField.getValue()
                )
        ));
    }

    private void handleSetMeetingResult(SetMeetingResult result) {
        var notificationVariant = result.success()
                ? NotificationVariant.LUMO_SUCCESS
                : NotificationVariant.LUMO_ERROR;
        var notificationText = result.success()
                ? MEETING_IS_SCHEDULED
                : UNABLE_SET_MEETING_TEXT;
        var notification = new Notification(notificationText, NOTIFICATION_DURATION, Notification.Position.TOP_STRETCH);
        notification.addThemeVariants(notificationVariant);
        if (!result.success()) {
            meetingStartField.setValue(result.available());
            meetingEndField.setValue(result.available().plus(STEP));
        } else {
            clearForm();
        }
        notification.open();
    }

    private void clearForm() {
        form.getChildren()
                .filter(HasValue.class::isInstance)
                .map(HasValue.class::cast)
                .forEach(HasValue::clear);
        meetingStartField.setValue(INITIAL_MEETING_START);
        meetingEndField.setValue(INITIAL_MEETING_END);
    }

    private FormLayout buildForm() {
        return new FormLayout() {{
            add(titleField, meetingStartField, meetingEndField, descriptionField);
            setMaxWidth(70f, Unit.VH);
            setAlignSelf(Alignment.CENTER, this);
            setColspan(titleField, 2);
            setColspan(descriptionField, 2);
            setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("500px", 2));
        }};
    }

    private H1 buildTitle() {
        return new H1(TITLE_TEXT) {{
            getStyle().set("margin", "0")
                    .set("text-align", "center");
        }};
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }
}
