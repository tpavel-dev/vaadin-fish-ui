package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.io.Serializable;

public class ConfirmationDialog extends Window {

    private String message;
    private ConfirmListener confirmListener;

    public ConfirmationDialog(String message, ConfirmListener confirmListener) {
        this.message = message;
        this.confirmListener = confirmListener;
        init();
    }

    private void init() {
        center();
        setSizeUndefined();
        buildLayout();
    }

    private void buildLayout() {
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);

        HorizontalLayout textLayout = new HorizontalLayout();
        textLayout.setMargin(true);
        Label text = new Label(message);
        textLayout.addComponent(text);
        content.addComponent(textLayout);

        HorizontalLayout actionButtons = new HorizontalLayout();
        Button okBtn = new Button("Yes");
        okBtn.setIcon(FontAwesome.CHECK);
        okBtn.addClickListener( e -> {
            confirmListener.onConfirm(e);
            close();
        });
        actionButtons.addComponent(okBtn);

        Button cancelBtn = new Button("No");
        cancelBtn.setIcon(FontAwesome.REMOVE);
        cancelBtn.addClickListener( e -> close());
        actionButtons.addComponent(cancelBtn);

        content.addComponent(actionButtons);
        content.setComponentAlignment(actionButtons, Alignment.MIDDLE_RIGHT);

        setContent(content);
    }

    @FunctionalInterface
    public interface ConfirmListener extends Serializable {
        void onConfirm(Button.ClickEvent event);
    }
}
