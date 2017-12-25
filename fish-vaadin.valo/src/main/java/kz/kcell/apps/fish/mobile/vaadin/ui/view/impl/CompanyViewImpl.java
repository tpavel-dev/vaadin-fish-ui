package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.model.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompaniesView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompanyView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.LinkedHashSet;
import java.util.Set;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.company_page_title;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

@SpringView(name = ViewsCode.name_company)
public class CompanyViewImpl extends BaseNavigationView implements CompanyView {
    public static final String VIEW_NAME = "company";

    private Company company;

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
        company = VaadinSession.getCurrent().getAttribute(Company.class);
    }

    private HorizontalLayout buildForm() {
        VerticalLayout leftSide = new VerticalLayout();
        VerticalLayout rightSide = new VerticalLayout();

        leftSide.addComponent(new TextField(""));

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(leftSide);
        layout.addComponent(rightSide);
        return layout;
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

        h.addComponent(buildForm());
        h.setSpacing(true);
        h.setMargin(new MarginInfo(false, false, true, false));

        content.addComponent(h);

//        content.addComponent(optionGroup);
    }


    public void translate() {
        setTitle($(company_page_title));
    }

}
