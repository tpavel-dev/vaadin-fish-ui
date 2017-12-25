package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.event.selection.SelectionEvent;
import kz.kcell.app.bonus_cmdr.model.Company;
import kz.kcell.vaadin.ui.View;

import java.util.Collection;

public interface CompaniesView extends View<CompaniesView.Listener> {

    interface Listener {

        Collection<Company> getAllCompanies();

        void setView(CompaniesView components);

        void onRowClick(SelectionEvent event);

    }

}
