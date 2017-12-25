package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.fish.mobile.vaadin.ui.ErrorHandler;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.BonusesInfoView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
@SpringView(name = ViewsCode.name_bonuses_info)
public class BonusesInfoViewImpl extends BaseNavigationView implements BonusesInfoView {

    @Autowired @Getter
    private BonusesInfoView.Listener listener;

    private Double bonus = null;

    private Button requestBonusBtn = new Button();
    private Label bonusesLable;

    public BonusesInfoViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }

    @Override
    public void translate() {
        super.translate();
//        setTitle(bonuses_info_title));
        requestBonusBtn.setCaption($(bonuses_request_bonus_caption));
        updateBonusLabel();
    }

    @Override
    protected void injectInit() {
        bonusesLable = new Label($(bonuses_info_no_bonus_label));
        bonusesLable.setStyleName(ValoTheme.LABEL_H2);
        requestBonusBtn.addClickListener(e -> {

            try {
                listener.requestBonus();
            } catch (Exception exc) {
                ErrorHandler.handle(exc);
            }

        });
    }

    @Override
    protected void initIds() {
        super.initIds();
        bonusesLable.setId(transfer_notif_label.name());
        requestBonusBtn .setId(transfer_request_bonus_caption.name());
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        listener.enter(viewChangeEvent);
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.setSizeUndefined();
        content.setSpacing(true);
        content.setMargin(new MarginInfo(false, true, true, true));
//        Responsive.makeResponsive(content);

        content.addComponents(bonusesLable, requestBonusBtn);
    }

    @Override
    public void setBonus(Double bonus) {
        this.bonus = bonus;
        updateBonusLabel();
    }

    private void updateBonusLabel() {
        if(bonus == null) {
            bonusesLable.setValue($(bonuses_info_no_bonus_label));
        } else if(bonus > 0) {
            bonusesLable.setValue($(bonuses_info_bonus_label, bonus));
        }

    }
}
