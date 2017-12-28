package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.app.bonus_cmdr.ws.stub.User;
import kz.kcell.apps.bonus_cmdr.model.AccessGroup;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompaniesView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.company_page_title;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

@SpringView(name = ViewsCode.name_companies)
public class CompaniesViewImpl extends BaseNavigationView implements CompaniesView {
    public static final String VIEW_NAME = "companies";

    @Autowired
    @Getter
    private CompaniesView.Listener listener;

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
    }

    private HorizontalLayout buildActionButtons() {
        HorizontalLayout actionButtonLayout = new HorizontalLayout();
        Button addCompanyBtn = new Button("Add company");
        addCompanyBtn.addClickListener(event -> {
            Window window = new Window();
            window.center();
            VerticalLayout content = new VerticalLayout();
            TextField name = new TextField("Name:");
            content.addComponent(name);

            HorizontalLayout actionBtns = new HorizontalLayout();
            Button saveBtn = new Button("Save");
            saveBtn.setIcon(FontAwesome.SAVE);
            saveBtn.addClickListener(event1 -> {
                Company company = new Company();
                company.setName(name.getValue());
                listener.saveCompany(company);
                window.close();
                showNotification("Company successfully added!", Notification.Type.HUMANIZED_MESSAGE);
                refreshTable();
            });
            actionBtns.addComponent(saveBtn);

            Button closeBtn = new Button("Close");
            closeBtn.setIcon(FontAwesome.CLOSE);
            closeBtn.addClickListener(event1 -> {
                window.close();
            });
            actionBtns.addComponent(closeBtn);

            content.addComponent(actionBtns);
            window.setContent(content);
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

        TextField taskField = new TextField();
        grid.addColumn(Company::getName).setCaption("Рекламная кампания").setEditorComponent(
                taskField, Company::setName).setExpandRatio(1);
        grid.getEditor().addSaveListener(event -> {
            listener.updateCompany(event.getBean());
            showNotification("Company succesfully edited!", Notification.Type.HUMANIZED_MESSAGE);
        });

        if (AccessGroup.SUPERVISOR.name().equals(currentUser.getAccessGroup()))
            grid.getEditor().setEnabled(true);

        grid.addComponentColumn(company -> {
            Button enterBtn = new Button("");
            enterBtn.setIcon(FontAwesome.INFO);
            enterBtn.addClickListener(event -> {
                listener.onRowClick(company);
            });
            return enterBtn;
        }).setCaption("Инфо");

        if (AccessGroup.SUPERVISOR.name().equals(currentUser.getAccessGroup())) {
            grid.addComponentColumn(company -> {
                Button removeBtn = new Button("");
                removeBtn.setIcon(FontAwesome.REMOVE);
                removeBtn.addClickListener(event -> {
                    listener.removeCompany(company);
                    showNotification("Company succesfully deleted!", Notification.Type.HUMANIZED_MESSAGE);
                    refreshTable();
                });
                return removeBtn;
            }).setCaption("Удалить");
        }

        grid.setColumnReorderingAllowed(true);
        grid.setDataProvider(new ListDataProvider<>(listener.getAllCompanies()));

        return grid;
    }

    private void refreshTable() {
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

        if (AccessGroup.SUPERVISOR.name().equals(currentUser.getAccessGroup()))
            v.addComponent(buildActionButtons());
        v.addComponent(table);
        v.setSpacing(true);
        v.setMargin(new MarginInfo(false, true, true, false));

        content.addComponent(v);

//        content.addComponent(optionGroup);
    }


    public void translate() {
        setTitle($(company_page_title));
    }

}
