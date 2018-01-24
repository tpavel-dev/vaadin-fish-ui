package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.*;
import kz.kcell.apps.bonus_cmdr.model.AccessGroup;
import kz.kcell.apps.bonus_cmdr.model.AccessGroupUtils;
import kz.kcell.apps.bonus_cmdr.model.NotificationUtils;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.component.BonusInfo;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.window.BonusWindow;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.window.ConfirmationDialog;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.window.SelectFileMsisdnWindow;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringView(name = ViewsCode.name_company)
public class CompanyViewImpl extends BaseNavigationView implements CompanyView {
    public static final String VIEW_NAME = "company";

    private Company company;
    private BonusParams selectedBonus;
    private List<BonusParams> bonusParamsList;
    private User currentUser;
    private BonusWindow window;
    private BonusInfo bonusInfo;

    private Grid<BonusParams> grid;

    @Autowired
    @Getter
    private CompanyView.Listener listener;

    public CompanyViewImpl() {
    }

    @PostConstruct
    public void init() {
        super.init();
    }

    @Override
    protected void injectInit() {
        currentUser = listener.getCurrentUser();
        company = listener.getCompany();
        bonusParamsList = listener.getBonusParamsByCompanyId(company.getCid());

        window = new BonusWindow(listener, company);
        window.addCloseListener(e -> refreshTable());
        bonusInfo = new BonusInfo(listener, null, AccessGroupUtils.checkAccess(AccessGroup.BONUS_CMDR_SUPERVISOR.name(), currentUser.getAccessGroups())
);
    }

    @Override
    protected void initIds() {
        super.initIds();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.setSizeUndefined();
        content.setWidth(100, Unit.PERCENTAGE);
        content.setSpacing(true);

        VerticalLayout v = new VerticalLayout();
        v.setWidth(100, Unit.PERCENTAGE);
        if (AccessGroupUtils.checkAccess(AccessGroup.BONUS_CMDR_SUPERVISOR.name(), currentUser.getAccessGroups()))
            v.addComponent(buildTopActionButtons());
        v.addComponent(buildGrid());
        v.setSpacing(true);
        v.setMargin(new MarginInfo(false, true, true, false));

        content.addComponent(v);
        content.addComponent(bonusInfo);
        content.addComponent(buildBottomActionButtons());
    }

    private Component addBonusAction(BonusParams bonus) {
        Button actionBtn = new Button();
        if (bonus.getStatus() == null) return null;
        switch (bonus.getStatus()) {
            case STOPED:
                actionBtn.setIcon(FontAwesome.PLAY);
                actionBtn.addClickListener(event -> {
                    listener.startBonusJob(bonus);
                    refreshTable();
                });
                break;
            case RUNNING:
                actionBtn.setIcon(FontAwesome.PAUSE);
                actionBtn.addClickListener(event -> {
                    listener.stopBonusJob(bonus);
                    refreshTable();
                });
                break;
            case COMPLATED: return null;
            default:return null;
        }
        return actionBtn;
    }

    private Grid<BonusParams> buildGrid() {

        grid = new Grid<>();
        grid.setWidth(100, Unit.PERCENTAGE);

        grid.addColumn(BonusParams::getBid).setCaption("BID");
        grid.addColumn(BonusParams::getAllowanceName).setCaption("Name");
        grid.addColumn(BonusParams::getAllowanceDescr).setCaption("Description");
        grid.addColumn(BonusParams::getExeOrder).setCaption("Order");
        grid.addColumn(BonusParams::getStatus).setCaption("Status");
        if (AccessGroupUtils.checkAccess(AccessGroup.BONUS_CMDR_EXECUTOR.name(), currentUser.getAccessGroups())) {
            grid.addComponentColumn(bonus -> addBonusAction(bonus))
                    .setMaximumWidth(100).setCaption("Change");
        }
        grid.addSelectionListener(event -> {
            onInfo(event);
        });
        if (AccessGroupUtils.checkAccess(AccessGroup.BONUS_CMDR_SUPERVISOR.name(), currentUser.getAccessGroups())) {
            grid.addComponentColumn(bonus -> {
                Button removeBtn = new Button();
                removeBtn.setIcon(FontAwesome.REMOVE);
                removeBtn.addClickListener(event -> {
                    onRemove(bonus);
                });
                return removeBtn;
            }).setCaption("Remove").setMaximumWidth(100);
        }

        grid.setItems(bonusParamsList);
        return grid;
    }

    private void refreshTable() {
        grid.setItems(listener.getBonusParamsByCompanyId(company.getCid()));
        grid.getDataProvider().refreshAll();
    }

    private void onInfo(SelectionEvent<BonusParams> event) {
        if (event.getFirstSelectedItem().equals(Optional.empty())) return;
        bonusInfo.setValue(event.getFirstSelectedItem().get());
    }

    private void onRemove(BonusParams bonus) {
        UI.getCurrent().addWindow(new ConfirmationDialog(
                "Are you sure to delete bonus (" + bonus.getAllowanceName() + ") ?",
                e -> {
                    listener.removeBonus(bonus);
                    refreshTable();
                    NotificationUtils.show("Bonus successfully deleted!", Notification.Type.HUMANIZED_MESSAGE);
                }));
    }

    private HorizontalLayout buildTopActionButtons() {
        HorizontalLayout actionButtonLayout = new HorizontalLayout();
        Button addCompanyBtn = new Button("Add bonus");
        addCompanyBtn.addClickListener(event -> {
            UI.getCurrent().addWindow(window);
        });
        addCompanyBtn.setIcon(FontAwesome.PLUS);
        actionButtonLayout.addComponent(addCompanyBtn);

        Button buildCompany = new Button("Build Company");
        buildCompany.addClickListener(e -> {
            listener.buildCompany(company.getCid());
            NotificationUtils.show("Company succesfully builded!", Notification.Type.HUMANIZED_MESSAGE);
        });
        actionButtonLayout.addComponent(buildCompany);

        Button uploadMsisdnBtn = new Button("Select MSISDNs");
        uploadMsisdnBtn.addClickListener(e -> {
            UI.getCurrent().addWindow(new SelectFileMsisdnWindow(listener, company));
        });
        actionButtonLayout.addComponent(uploadMsisdnBtn);

        Button clearCompanyBtn = new Button("Clear company");
        clearCompanyBtn.addClickListener(e -> {
            listener.clearCompany(company.getCid());
            NotificationUtils.show("Company successfully cleared", Notification.Type.HUMANIZED_MESSAGE);
        });
        actionButtonLayout.addComponent(clearCompanyBtn);

        return actionButtonLayout;
    }

    private HorizontalLayout buildBottomActionButtons() {
        HorizontalLayout actionButtons = new HorizontalLayout();
        actionButtons.setSpacing(true);
        actionButtons.setMargin(new MarginInfo(false, false, true, false));
        Button backBtn = new Button("Back", event -> listener.showCompaniesView());
        backBtn.setIcon(FontAwesome.ARROW_LEFT);
        actionButtons.addComponent(backBtn);

        if (AccessGroupUtils.checkAccess(AccessGroup.BONUS_CMDR_SUPERVISOR.name(), currentUser.getAccessGroups())) {
            Button saveBtn = new Button("Save", event -> saveBonusInfo());
            saveBtn.setIcon(FontAwesome.SAVE);
            actionButtons.addComponent(saveBtn);
        }
        return actionButtons;
    }

    private void saveBonusInfo() {
        BonusParams bonusParams = bonusInfo.getBean();
        if (bonusParams.getBid() != null) {
            listener.updateBonus(bonusParams);
            NotificationUtils.show("Bonus successfully edited!", Notification.Type.HUMANIZED_MESSAGE);
            refreshTable();
        } else {
            NotificationUtils.show("Select bonus", Notification.Type.ERROR_MESSAGE);
        }
    }

    public void translate() {
        setTitle("Компания: " + company.getName());
    }

}
