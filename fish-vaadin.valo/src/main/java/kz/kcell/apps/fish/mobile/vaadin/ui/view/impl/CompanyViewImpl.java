package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.*;
import kz.kcell.apps.bonus_cmdr.model.AccessGroup;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.component.BonusInfo;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.window.BonusWindow;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private Boolean isSupervisor;

    private Grid<BonusParams> grid;

    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

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

        isSupervisor = AccessGroup.SUPERVISOR.name().equals(currentUser.getAccessGroup());

        window = new BonusWindow(listener, company);
        bonusInfo = new BonusInfo(listener, null, isSupervisor);
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
        if (AccessGroup.SUPERVISOR.name().equals(currentUser.getAccessGroup()))
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
        grid.addComponentColumn(bonus -> addBonusAction(bonus))
                .setMaximumWidth(100).setCaption("Change");
        grid.addSelectionListener(event -> {
            onInfo(event);
        });
        if (isSupervisor) {
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
        grid.setItems(bonusParamsList);
    }

    private void onInfo(SelectionEvent<BonusParams> event) {
        if (event.getFirstSelectedItem().equals(Optional.empty())) return;
        bonusInfo.setBonus(event.getFirstSelectedItem().get());
    }

    private void onRemove(BonusParams bonus) {
        listener.removeBonus(bonus);
        refreshTable();
        showNotification("Bonus successfully deleted!", Notification.Type.HUMANIZED_MESSAGE);
    }

    private HorizontalLayout buildTopActionButtons() {
        HorizontalLayout actionButtonLayout = new HorizontalLayout();
        Button addCompanyBtn = new Button("Add bonus");
        addCompanyBtn.addClickListener(event -> {
            UI.getCurrent().addWindow(window);
        });
        addCompanyBtn.setIcon(FontAwesome.PLUS);
        actionButtonLayout.addComponent(addCompanyBtn);

        return actionButtonLayout;
    }

    private HorizontalLayout buildBottomActionButtons() {
        HorizontalLayout actionButtons = new HorizontalLayout();
        actionButtons.setSpacing(true);
        actionButtons.setMargin(false);
        Button backBtn = new Button("Back", event -> {
            listener.showCompaniesView();
        });
        backBtn.setIcon(FontAwesome.ARROW_LEFT);
        actionButtons.addComponent(backBtn);

        if (isSupervisor) {
            Button saveBtn = new Button("Save", event -> {
                saveBonusInfo();
            });
            saveBtn.setIcon(FontAwesome.SAVE);
            actionButtons.addComponent(saveBtn);
        }
        return actionButtons;
    }

    private void saveBonusInfo() {
        BonusParams bonusParams = bonusInfo.getBean();
        listener.updateBonus(bonusParams);
    }

    public void translate() {
        setTitle("Компания: " + company.getName());
    }

}
