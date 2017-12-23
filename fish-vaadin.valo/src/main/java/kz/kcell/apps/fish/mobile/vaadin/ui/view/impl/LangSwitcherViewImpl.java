package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import kz.kcell.apps.common.Language;
import kz.kcell.apps.fish.mobile.vaadin.ui.MainUI;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.LangSwitcherView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.fish.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 02 11 2014
 */
@SpringView(name = ViewsCode.name_change_lang)
public class LangSwitcherViewImpl extends BaseNavigationView implements LangSwitcherView{
    public static final String VIEW_NAME = "change_lang";

    @Autowired
    @Getter
    private MainUI.Listener listener;

    private Button ruButton = new Button();
    private Button kkButton = new Button();


    public LangSwitcherViewImpl() {
    }

    @PostConstruct
    public void init() {
//        listener.setView(this);
        super.init();
    }


    @Override
    protected void injectInit() {
        ruButton.setCaption($(language_name_ru));
        kkButton.setCaption($(language_name_kk));

        ruButton.addClickListener(e -> {
            translate();
            listener.onChangeLanguage(Language.RU);
        });
        kkButton.addClickListener(e -> {
            translate();
            listener.onChangeLanguage(Language.KK);
        });
//        ruButton.addStyleName(ValoTheme.BUTTON_LINK);
//        kkButton.addStyleName(ValoTheme.BUTTON_LINK);

/*
        optionGroup = new OptionGroup();
        optionGroup.setMultiSelect(false);
        optionGroup.addItems(Language.RU);
//        optionGroup.addItems(Language.KZ);
        optionGroup.addItems(Language.KK);
        optionGroup.addValueChangeListener(
            e -> {listener.onChangeLanguage((Language) optionGroup.getValue());}
        );
*/
    }

    @Override
    protected void initIds() {
        super.initIds();
        ruButton.setId(language_name_ru.name());
        kkButton.setId(language_name_kk.name());
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.setSizeUndefined();
        content.setSpacing(true);
//        content.setMargin(false);
//        Responsive.makeResponsive(content);
//        content.addComponents(kkButton, ruButton);

        HorizontalLayout h = new HorizontalLayout();
        h.addComponents(kkButton, ruButton);
        h.setSpacing(true);
        h.setMargin(new MarginInfo(false, false, true, false));

        content.addComponent(h);

//        content.addComponent(optionGroup);
    }


    public void translate() {
        setTitle($(main_menu_change_lang));
    }

}
