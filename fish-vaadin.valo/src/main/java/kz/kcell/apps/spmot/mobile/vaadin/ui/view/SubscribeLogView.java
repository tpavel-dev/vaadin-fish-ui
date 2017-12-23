package kz.kcell.apps.spmot.mobile.vaadin.ui.view;


import com.vaadin.data.HasValue;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.DateField;
import kz.kcell.apps.spmot.domain.spmot.entity.Subscribe;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.impl.SubscribeLogViewImpl;
import kz.kcell.vaadin.ui.View;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
public interface SubscribeLogView extends View<SubscribeLogView.Listener> {

    DateField getTo();

    DateField getFrom();

    void updateTableData();

    interface Listener {
//        Subscribe fetchLog();
        Collection<Subscribe> fetchLog(LocalDate startDate, LocalDate endDate);

        String buildStatusLabelText(Subscribe subscribe);

        void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent);

        void changeFrom(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent);

        void changeTo(HasValue.ValueChangeEvent<LocalDate> valueChangeEvent);

        void setView(SubscribeLogView components);
    }

}


