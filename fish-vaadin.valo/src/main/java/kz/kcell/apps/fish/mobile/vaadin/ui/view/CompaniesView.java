package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.event.selection.SelectionEvent;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.app.bonus_cmdr.ws.stub.User;
import kz.kcell.vaadin.ui.View;

import java.util.Collection;

public interface CompaniesView extends View<CompaniesView.Listener> {

    interface Listener {

        Collection<Company> getAllCompanies();

        void setView(CompaniesView components);

        void onRowClick(Company company);

        void removeCompany(Company company);

        void updateCompany(Company company);

        void saveCompany(Company company);

        User getCurrentUser();
    }

}
