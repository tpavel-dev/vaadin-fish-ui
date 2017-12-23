package kz.kcell.apps.spmot.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.DashboardView;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import static kz.kcell.apps.spmot.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.spmot.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
@SpringView(name = ViewsCode.name_dashboard)
public class DashboardViewImpl extends BaseNavigationView implements DashboardView {
    public static final String VIEW_NAME = "dashboard";

    @Autowired
    @Getter
    private DashboardViewListener listener;

    private Button main_menu_button_product       ;
    private Button main_menu_button_subscribe_log ;
    private Button main_menu_button_transfer_bonus;
    private Button main_menu_button_bonus_info    ;
    private Button main_menu_button_change_lang   ;
    private Button main_menu_button_out           ;
    private Button main_menu_button_login         ;
    private Button main_menu_button_bonus         ;
//    private Button main_menu_button_captcha       ;
    private boolean first_init = true;

    private ArrayList<Button> buttons = new ArrayList<>();
    private CssLayout l;


    public DashboardViewImpl() {}

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }


    @Override
    public void translate() {
        main_menu_button_product        .setCaption($(main_menu_product));
        main_menu_button_subscribe_log  .setCaption($(main_menu_subscribe_log));
        main_menu_button_transfer_bonus .setCaption($(main_menu_transfer_bonus));
        main_menu_button_change_lang    .setCaption($(main_menu_change_lang));
        main_menu_button_out            .setCaption($(main_menu_out));
//        main_menu_button_captcha        .setCaption("captcha");
    }


    @Override
    protected void initIds() {
        super.initIds();
        main_menu_button_product        .setId(main_menu_product        .name());
        main_menu_button_subscribe_log  .setId(main_menu_subscribe_log.name());
        main_menu_button_transfer_bonus .setId(main_menu_transfer_bonus.name());
        main_menu_button_bonus_info     .setId(main_menu_bonus_info.name());
        main_menu_button_change_lang    .setId(main_menu_change_lang    .name());
        main_menu_button_out            .setId(main_menu_out.name());
//        main_menu_button_captcha        .setId("captcha");
    }

    @Override
    protected void injectInit() {
/*
        main_menu_button_product        = new Button(main_menu_product        .$(), e-> {navigator.navigateTo(ValoMainUIImpl.State.services.name()   );});
        main_menu_button_subscribe_log  = new Button(main_menu_subscribe_log  .$(), e-> {navigator.navigateTo(ValoMainUIImpl.State.log.name()        );});
        main_menu_button_transfer_bonus = new Button(main_menu_transfer_bonus .$(), e-> {navigator.navigateTo(ValoMainUIImpl.State.transfer.name()   );});
        main_menu_button_bonus_info     = new Button(main_menu_bonus_info     .$(), e-> {navigator.navigateTo(ValoMainUIImpl.State.bonuses_info.name());});
        main_menu_button_change_lang    = new Button(main_menu_change_lang    .$(), e-> {navigator.navigateTo(ValoMainUIImpl.State.change_lang.name());});
        main_menu_button_out            = new Button(main_menu_out            .$(), e-> {navigator.navigateTo(ValoMainUIImpl.State.logout.name()     );});
*/
        main_menu_button_product        = new Button($(main_menu_product       ), e-> {listener.onClick(ViewsCode.services);});
        main_menu_button_subscribe_log  = new Button($(main_menu_subscribe_log ), e-> {listener.onClick(ViewsCode.log);});
        main_menu_button_transfer_bonus = new Button($(main_menu_transfer_bonus), e-> {listener.onClick(ViewsCode.transfer);});
        main_menu_button_bonus_info     = new Button($(main_menu_bonus_info    ), e-> {listener.onClick(ViewsCode.bonuses_info);});
        main_menu_button_change_lang    = new Button($(main_menu_change_lang   ), e-> {listener.onClick(ViewsCode.change_lang);});
        main_menu_button_out            = new Button($(main_menu_out           ), e-> {listener.onClick(ViewsCode.logout);});
//        main_menu_button_captcha        = new Button($(main_menu_change_lang   ), e-> {listener.onClick(ValoMainUIImpl.State.captcha);});

        buttons.add(main_menu_button_product);
        buttons.add(main_menu_button_subscribe_log);
        buttons.add(main_menu_button_transfer_bonus);
        buttons.add(main_menu_button_bonus_info);
        buttons.add(main_menu_button_change_lang);
        buttons.add(main_menu_button_out);
//        buttons.add(main_menu_button_captcha);

    }

    @Override
    protected void buildLayout() {
//      HorizontalLayout l = new HorizontalLayout();
        l = new CssLayout();
        content.addComponents(l);
        l.addComponents(
                  main_menu_button_product
                , main_menu_button_subscribe_log
                , main_menu_button_transfer_bonus
                , main_menu_button_bonus_info
                , main_menu_button_change_lang
                , main_menu_button_out
//                , main_menu_button_captcha
        );

        l.forEach(this::applyStyle);

        l.setStyleName("responsive");
        l.setSizeFull();
        Responsive.makeResponsive(l);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
/*
        if(first_init) {
            first_init = false;
            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                    l.forEach(p -> applyStyle(p));
                }
            });
        }
*/
    }

    private void applyStyle(Component c) {
        c.addStyleName("res-button");
        c.addStyleName(ValoTheme.BUTTON_PRIMARY);
    }

/*
    private void applyStyle(Component c) {
        if(Page.getCurrent() != null) {
            int i = Page.getCurrent().getBrowserWindowWidth() / 3-1;
            c.setWidth(i, Unit.PIXELS);
            c.setHeight(i, Unit.PIXELS);
//            c.setWidth(i, Unit.EM);
//            c.setHeight(i, Unit.EM);
            c.setStyleName(ValoTheme.BUTTON_PRIMARY);
//            c.setStyleName("myresponsive");
            Responsive.makeResponsive(c);
        }

    }
*/


}
