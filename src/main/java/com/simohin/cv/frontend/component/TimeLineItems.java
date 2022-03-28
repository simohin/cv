package com.simohin.cv.frontend.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeLineItems extends VerticalLayout {

    private Component[] items;

    public TimeLineItems(Collection<TimeLineItem> items) {
        setItems(items);
        setMaxWidth(90f, Unit.VH);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignSelf(Alignment.CENTER, this);
        setAlignItems(Alignment.CENTER);
        add(this.items);
    }

    public void setItems(Collection<TimeLineItem> items) {
        this.items = items.toArray(new Component[0]);
    }
}
