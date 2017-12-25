package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.event.selection.SelectionEvent;
import kz.kcell.app.bonus_cmdr.model.Company;
import kz.kcell.vaadin.ui.View;

import java.util.Collection;

public interface CompanyView extends View<CompanyView.Listener> {

    interface Listener {

        Collection<Company> getAllCompanies();

        void setView(CompanyView components);

        void onRowClick(SelectionEvent event);

    }

}
