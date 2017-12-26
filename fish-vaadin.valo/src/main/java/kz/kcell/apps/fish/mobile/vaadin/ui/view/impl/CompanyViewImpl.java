package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.BonusParams;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompaniesView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.company_page_title;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

@SpringView(name = ViewsCode.name_company)
public class CompanyViewImpl extends BaseNavigationView implements CompanyView {
    public static final String VIEW_NAME = "company";

    private Company company;
    private List<BonusParams> bonusParamsList;

    private Grid<BonusParams> grid;
    private static final Set<Grid.Column<BonusParams, ?>>
            collapsibleColumns = new LinkedHashSet<>();

    @Autowired
    @Getter
    private CompanyView.Listener listener;

    public CompanyViewImpl() {
    }

    @PostConstruct
    public void init() {
//        listener.setView(this);
        super.init();
    }

    @Override
    protected void injectInit() {
        company = listener.getCompany();
        bonusParamsList = listener.getBonusParamsByCompanyId(company.getCid());
    }

    private VerticalLayout buildCompanyInfo() {
        VerticalLayout leftSide = new VerticalLayout();
        VerticalLayout rightSide = new VerticalLayout();

        leftSide.addComponent(new Label("Company: "));
        rightSide.addComponent(new Label(company.getName() + "(" + company.getCid() + ")"));

        HorizontalLayout companyInfo = new HorizontalLayout();
        companyInfo.addComponent(leftSide);
        companyInfo.addComponent(rightSide);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(companyInfo);
        layout.addComponent(buildGrid() );
        return layout;
    }

    private Grid<BonusParams> buildGrid() {
        grid = new Grid<>();
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getCid)
                        .setCaption("CID"));
        collapsibleColumns
                .add(grid.addColumn(BonusParams::getBid).setCaption("BID"));

        grid.setItems(bonusParamsList);

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
        h.setWidth(500, Unit.PIXELS);

        h.addComponent(buildCompanyInfo());
        h.setSpacing(true);
        h.setMargin(new MarginInfo(false, false, true, false));

        content.addComponent(h);

//        content.addComponent(optionGroup);
    }


    public void translate() {
        setTitle($(company_page_title));
    }

}
