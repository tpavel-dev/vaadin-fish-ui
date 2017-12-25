package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.data.HasValue;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import kz.kcell.apps.common.Format;
import kz.kcell.apps.bonus_cmdr.model.Subscribe;
import kz.kcell.apps.bonus_cmdr.model.SubscribeStatus;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.SubscribeLogView;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
@Slf4j
@SpringPresenter
public class SubscribeLogPresenter extends AbstractPresenter<SubscribeLogView> implements Presenter, SubscribeLogView.Listener {

    @Autowired
    private EventBus eventBus;

    @Setter
    private SubscribeLogView view;

    @Override
    protected SubscribeLogView getView() {
        return view;
    }

    @Override
    public Collection<Subscribe> fetchLog(LocalDate startDate, LocalDate endDate) {
        Collection<Subscribe> subscribeCollection = null;
        if(startDate != null && endDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            try {
                log.info("requested log records of the {} and the {}", startDate.format(formatter), endDate.format(formatter));
            }catch (Exception nothing) {}
        } else {
            log.info("requested last log records");
        }


        if (subscribeCollection != null && subscribeCollection.size() > 0) {
            log.info("return log records {}",subscribeCollection.size());
            return subscribeCollection;
        }

        log.info("return log records {}",0);
        return  Collections.emptyList();
    }

    public String buildStatusLabelText(Subscribe subscribe) {
        String valueStr = $(subscribe_log_state_unknow);

        if(subscribe.getStatus().equals(SubscribeStatus.ACTIVATED)) {
            valueStr = $(subscribe_log_bonus_collumn,
                    Format.edFormat.format(subscribe.getBonus()),
                    Format.edFormat.format(subscribe.getShareBonus())
            );

            if(subscribe.getBonus() > 0) {
                valueStr += $(subscribe_log_share_bonus,
                        Format.edFormat.format(subscribe.getBonus()),
                        Format.edFormat.format(subscribe.getShareBonus())
                );
            }

        } else if(subscribe.getStatus().name().startsWith(SubscribeStatus.REJECTED.name())) {
            valueStr = $(subscribe_log_state_reject);

        } else if(subscribe.getStatus().equals(SubscribeStatus.PENDING)) {
            valueStr = " -- ";
        } else  {
            log.error("Unknow subscribe status [{}]"+subscribe.getStatus());
        }
        return valueStr;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        view.updateTableData();
    }

    @Override
    public void changeFrom(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent) {
        if(view.getTo().getValue() == null) {
            view.getTo().setValue(view.getFrom().getValue());
        }

//        LocalDate locatFrom = TimeUtil.dateToLocal(view.getFrom().getValue());
//        LocalDate locatTo = TimeUtil.dateToLocal(view.getTo().getValue());
        LocalDate locatFrom = view.getFrom().getValue();
        LocalDate locatTo = view.getTo().getValue();

        Period period = Period.between(locatFrom, locatTo);
//            if(period.getDays() > 30) {
        if(period.getMonths() > 0) {
            locatTo = locatFrom.plusDays(30);
//            view.showNotification("Периода запрос не больше месяца, верхняя граница опущена", Notification.Type.ASSISTIVE_NOTIFICATION);
//            Notification.show("Периода запрос не больше месяца, верхняя граница опущена");
        }
        if(period.isNegative()) {
            locatTo = locatFrom;
        }

//        Date toDate = TimeUtil.localToDate(locatTo);
        view.getTo().setValue(locatTo);

        view.updateTableData();

    }

    @Override
    public void changeTo(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent) {

        if (view.getFrom().getValue() == null) {
            view.getFrom().setValue(view.getTo().getValue());
        }

        LocalDate locatFrom = view.getFrom().getValue();
        LocalDate locatTo   = view.getTo().getValue();

        Period period = Period.between(locatFrom, locatTo);
//            if(period.getDays() > 30) {
        if(period.getMonths() > 0) {
            locatFrom = locatTo.minusDays(30);
//            view.showNotification("Периода запрос не больше месяца, нижняя граница подтянута", Notification.Type.ASSISTIVE_NOTIFICATION);
//            Notification.show("Периода запрос не больше месяца, нижняя граница подтянута");
        }
        if(period.isNegative()) {
            locatFrom = locatTo;
        }

        view.getFrom().setValue(locatFrom);

        view.updateTableData();
    }

    @Override
    public void display(UI ui) {
//        ui.setContent(view);
    }

    @Override
    public void translate() {
        view.translate();
        view.updateTableData();
    }

}
