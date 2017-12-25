package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.StreamResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CaptchaView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.captcha_prompt;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

@SpringView(name = ViewsCode.name_captcha)
public class CaptchaViewImpl extends BaseNavigationView implements CaptchaView {

    private Label questionLabel = new Label();
//    private Label promptLabel = new Label();
    private Label inputValue = new Label();

    @Autowired
    @Getter
    private CaptchaViewListener listener;

    private Image image;

    public CaptchaViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();

        Button[] b = new Button[]{
                new Button("0", l -> listener.submit("0")),
                new Button("1", l -> listener.submit("1")),
                new Button("2", l -> listener.submit("2")),
                new Button("3", l -> listener.submit("3")),
                new Button("4", l -> listener.submit("4")),
                new Button("5", l -> listener.submit("5")),
                new Button("6", l -> listener.submit("6")),
                new Button("7", l -> listener.submit("7")),
                new Button("8", l -> listener.submit("8")),
                new Button("9", l -> listener.submit("9")),
        };

        HorizontalLayout h1 = new HorizontalLayout();
        h1.addComponents(b[1], b[2], b[3]);
        h1.setSpacing(false);
        h1.setHeightUndefined();

        HorizontalLayout h2 = new HorizontalLayout();
        h2.addComponents(b[4], b[5], b[6]);
        h2.setSpacing(false);
        h2.setHeightUndefined();

        HorizontalLayout h3 = new HorizontalLayout();
        h3.addComponents(b[7], b[8], b[9]);
        h3.setSpacing(false);
        h3.setHeightUndefined();

        HorizontalLayout h4 = new HorizontalLayout();
        h4.addComponents(b[0], new Button("Сброс", l -> listener.submit("10")));
        h4.setSpacing(false);
        h4.setHeightUndefined();

//        content.setSpacing(true);
//        content.setMargin(new MarginInfo(false, true, false, true));
//        content.setHeightUndefined();
        content.setSizeUndefined();
        VerticalLayout v = new VerticalLayout();

//        v.setMargin(new MarginInfo(false, true, false, true));
        v.setMargin(false);
        v.setSizeUndefined();
        v.setSpacing(true);
//        content.addComponentAsFirst(h);

        image = new Image("",  getCaptchaImg());
        image.setHeight("50");
        image.setWidthUndefined();

//        content.removeAllComponents();
        content.addComponents(
                image,
//                inputValue,
                h1, h2, h3, h4
        );

        content.addComponent(v);
    }

    public StreamResource getCaptchaImg() {
        return listener.getCaptchaImg();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        listener.enter(viewChangeEvent);
    }

    @Override
    public void setInputValue(String s) {
        showNotification(s, Notification.Type.HUMANIZED_MESSAGE);
//        inputValue.setValue(s);
    }

    @Override
    public void translate() {
//        setCaption($(captcha_prompt));
        image.setCaption($(captcha_prompt));
    }
}