package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import kz.kcell.app.bonus_cmdr.model.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.util.LinkedHashSet;
import java.util.Set;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

@SpringView(name = ViewsCode.name_companies)
public class CompanyViewImpl extends BaseNavigationView implements CompanyView {
    public static final String VIEW_NAME = "companies";

    @Autowired
    @Getter
    private CompanyView.Listener listener;

    private Grid<Company> table;
    private static final Set<Grid.Column<Company, ?>>
            collapsibleColumns = new LinkedHashSet<>();

    public CompanyViewImpl() {
    }

    @PostConstruct
    public void init() {
//        listener.setView(this);
        super.init();
    }

    @Override
    protected void injectInit() {
        table = buildGrid();
    }

    private Grid<Company> buildGrid() {
        Grid<Company> grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSizeFull();
        grid.setWidth("100%");
        grid.setHeight("100%");
        setMargin(new MarginInfo(false, true, false, true));

        collapsibleColumns
                .add(grid.addColumn(Company::getCid)
                        .setCaption("CID"));
        collapsibleColumns
                .add(grid.addColumn(Company::getName).setCaption("Рекламная кампания"));

        grid.addComponentColumn(person ->
                new Button("Info")
        );

        grid.setColumnReorderingAllowed(true);

        grid.setDataProvider(new ListDataProvider<>(listener.getAllCompanies()));

        grid.addSelectionListener(
                event -> listener.onRowClick(event));

        return grid;
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

        HorizontalLayout h = new HorizontalLayout();

        h.addComponent(table);
        h.setSpacing(true);
        h.setMargin(new MarginInfo(false, false, true, false));

        content.addComponent(h);

//        content.addComponent(optionGroup);
    }


    public void translate() {
        setTitle($(company_page_title));
    }

}
