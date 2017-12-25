package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.vaadin.ui.View;

import java.util.List;

public interface CompanyView extends View<CompanyView.Listener> {

    interface Listener {

        List<Company> getAllCompanies();

        void setView(CompanyView components);

    }

}
