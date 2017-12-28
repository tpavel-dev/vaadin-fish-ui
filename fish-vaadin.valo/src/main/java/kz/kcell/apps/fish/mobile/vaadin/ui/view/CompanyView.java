package kz.kcell.apps.fish.mobile.vaadin.ui.view;

import com.vaadin.event.selection.SelectionEvent;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusAssigmentState;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.app.bonus_cmdr.ws.stub.User;
import kz.kcell.vaadin.ui.View;

import java.util.List;

public interface CompanyView extends View<CompanyView.Listener> {

    interface Listener {

        List<BonusParams> getBonusParamsByCompanyId(Long cid);

        void setView(CompanyView components);

        void onRowClick(SelectionEvent event);

        void startBonusJob(BonusParams bonusParams);

        void stopBonusJob(BonusParams bonusParams);

        BonusAssigmentState getBonusAssigmentState(BonusParams bonusParams);

        void updateBonus(BonusParams bonusParams);

        void showCompaniesView();

        User getCurrentUser();

        void removeBonus(BonusParams bonusParams);

        Company getCompany();

        void saveBonus(BonusParams bonusParams);
    }

}
