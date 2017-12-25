package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import kz.kcell.app.bonus_cmdr.model.Company;
import kz.kcell.vaadin.ui.View;

public interface CompanyView extends View<CompanyView.Listener> {

    interface Listener {

        void setView(CompanyView components);

        void updateCompany(Company company);

    }

}
