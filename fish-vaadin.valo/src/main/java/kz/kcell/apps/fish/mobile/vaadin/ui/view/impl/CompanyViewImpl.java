package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

@SpringView(name = ViewsCode.name_companies)
public class CompanyViewImpl extends BaseNavigationView implements CompanyView {
    public static final String VIEW_NAME = "companies";

    @Autowired
    @Getter
    private CompanyView.Listener listener;

    private Grid<Company> table;

    public CompanyViewImpl() {
    }

    @PostConstruct
    public void init() {
//        listener.setView(this);
        super.init();
    }


    @Override
    protected void injectInit() {
        table = new Grid();
        table.setSelectionMode(Grid.SelectionMode.SINGLE);
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
