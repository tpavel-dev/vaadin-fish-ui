package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.app.bonus_cmdr.ws.stub.User;
import kz.kcell.apps.bonus_cmdr.model.AccessGroup;
import kz.kcell.apps.bonus_cmdr.model.AccessGroupUtils;
import kz.kcell.apps.bonus_cmdr.model.NotificationUtils;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompaniesView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.component.BonusInfo;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.component.CompanyInfo;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.window.BonusWindow;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.window.CompanyWindow;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.window.ConfirmationDialog;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.util.Optional;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.company_page_title;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

@SpringView(name = ViewsCode.name_companies)
public class CompaniesViewImpl extends BaseNavigationView implements CompaniesView {
    public static final String VIEW_NAME = "companies";

    @Autowired
    @Getter
    private CompaniesView.Listener listener;

    private CompanyWindow window;
    private CompanyInfo companyInfo;

    private User currentUser;
    private Grid<Company> table;

    public CompaniesViewImpl() {
    }

    @PostConstruct
    public void init() {
//        listener.setView(this);
        super.init();
    }

    @Override
    protected void injectInit() {
        currentUser = listener.getCurrentUser();
        table = buildGrid();

        window = new CompanyWindow(listener);
        window.addCloseListener(e -> refreshTable());
        companyInfo = new CompanyInfo(listener, null,
                AccessGroupUtils.checkAccess(AccessGroup.SUPERVISOR.name(),
                        currentUser.getAccessGroups()));
    }

    private HorizontalLayout buildActionButtons() {
        HorizontalLayout actionButtonLayout = new HorizontalLayout();
        Button addCompanyBtn = new Button("Add company");
        addCompanyBtn.addClickListener(event -> {
            UI.getCurrent().addWindow(window);
        });
        addCompanyBtn.setIcon(FontAwesome.PLUS);
        actionButtonLayout.addComponent(addCompanyBtn);

        return actionButtonLayout;
    }

    private Grid<Company> buildGrid() {
        Grid<Company> grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setWidth(100, Unit.PERCENTAGE);
            setMargin(new MarginInfo(false, true, false, true));

        grid.addColumn(Company::getCid).setCaption("CID");
        grid.addColumn(Company::getName).setCaption("Name").setExpandRatio(1);

        grid.addComponentColumn(company -> {
            Button enterBtn = new Button("");
            enterBtn.setIcon(FontAwesome.INFO);
            enterBtn.addClickListener(event -> {
                listener.onRowClick(company);
            });
            return enterBtn;
        }).setCaption("Инфо");

        grid.addSelectionListener(event -> {
            onInfo(event);
        });

        if (AccessGroupUtils.checkAccess(AccessGroup.SUPERVISOR.name(), currentUser.getAccessGroups())) {
            grid.addComponentColumn(company -> {
                Button removeBtn = new Button("");
                removeBtn.setIcon(FontAwesome.REMOVE);
                removeBtn.addClickListener(event -> {
                    onRemove(company);
                });
                return removeBtn;
            }).setCaption("Удалить");
        }

        grid.setColumnReorderingAllowed(true);
        grid.setDataProvider(new ListDataProvider<>(listener.getAllCompanies()));

        return grid;
    }

    public void refreshTable() {
        table.setItems(listener.getAllCompanies());
    }

    @Override
    protected void initIds() {
        super.initIds();
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.setSizeUndefined();
        content.setSpacing(true);
        content.setWidth(100, Unit.PERCENTAGE);

        VerticalLayout v = new VerticalLayout();
        v.setWidth(100, Unit.PERCENTAGE);

        if (AccessGroupUtils.checkAccess(AccessGroup.SUPERVISOR.name(), currentUser.getAccessGroups()))
            v.addComponent(buildActionButtons());
        v.addComponent(table);
        v.setSpacing(true);
        v.setMargin(new MarginInfo(false, true, true, false));

        content.addComponent(v);
        content.addComponent(companyInfo);
        content.addComponent(buildBottomActionButtons());

//        content.addComponent(optionGroup);
    }

    private HorizontalLayout buildBottomActionButtons() {
        HorizontalLayout actionButtons = new HorizontalLayout();
        actionButtons.setSpacing(true);
        actionButtons.setMargin(new MarginInfo(false, false, true, false));

        if (AccessGroupUtils.checkAccess(AccessGroup.SUPERVISOR.name(), currentUser.getAccessGroups())) {
            Button saveBtn = new Button("Save", event -> saveCompanyInfo());
            saveBtn.setIcon(FontAwesome.SAVE);
            actionButtons.addComponent(saveBtn);
        }
        return actionButtons;
    }

    private void onInfo(SelectionEvent<Company> event) {
        if (event.getFirstSelectedItem().equals(Optional.empty())) return;
        companyInfo.setValue(event.getFirstSelectedItem().get());
    }

    private void saveCompanyInfo() {
        Company com = companyInfo.getBean();
        if (com.getCid() != null) {
            listener.updateCompany(com);
            NotificationUtils.show("Company successfully edited!", Notification.Type.HUMANIZED_MESSAGE);
            refreshTable();
        } else {
            NotificationUtils.show("Select company", Notification.Type.ERROR_MESSAGE);
        }
    }

    private void onRemove(Company company) {
        UI.getCurrent().addWindow(new ConfirmationDialog(
                "Are you sure to delete company (" + company.getName() + ") ?",
                e -> {
                    listener.removeCompany(company);
                    refreshTable();
                    NotificationUtils.show("Company successfully deleted!", Notification.Type.HUMANIZED_MESSAGE);
                }));
    }

    public void translate() {
        setTitle($(company_page_title));
    }

}
