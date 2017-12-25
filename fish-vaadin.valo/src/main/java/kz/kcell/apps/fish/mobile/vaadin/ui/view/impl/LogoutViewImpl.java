package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;


import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.LogoutView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.logout_submit_button_caption;
import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.logout_title;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */

@SpringView(name = ViewsCode.name_logout)
public class LogoutViewImpl extends BaseNavigationView implements LogoutView {
    public static final String VIEW_NAME = "logout";

    @Autowired
    @Getter
    private LogoutView.Listener listener;

    private Button submit;

    public LogoutViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }

    @Override
    protected void injectInit() {
        setTitle($(logout_title));
        submit = new Button($(logout_submit_button_caption));
        submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
        submit.focus();
        submit.addClickListener(e -> {
            try {
                listener.onSubmit(e);
            } catch (BaseException e1) {
                ErrorHandler.handle(e1);
            }
        });
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();

        content.setSizeUndefined();
        content.setSpacing(true);
        Responsive.makeResponsive(content);

        content.addComponents(submit);

    }

    @Override
    public void translate() {
        setTitle($(logout_title));
        submit.setCaption($(logout_submit_button_caption));
    }

    @Override
    protected void initIds() {
        super.initIds();
        setId(logout_title.name());
        submit.setId(logout_submit_button_caption.name());

    }
}
