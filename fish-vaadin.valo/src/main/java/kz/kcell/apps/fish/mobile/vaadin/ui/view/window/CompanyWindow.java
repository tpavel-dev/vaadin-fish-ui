package kz.kcell.apps.fish.mobile.vaadin.ui.view.window;

import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.bonus_cmdr.model.NotificationUtils;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompaniesView;

public class CompanyWindow extends AbstractWindow<Company> {

    private CompaniesView.Listener listener;

    private TextField name;

    public CompanyWindow(CompaniesView.Listener listener) {
        super();
        this.listener = listener;
    }

    @Override
    void init() {
        center();
        setWidth(300, Unit.PIXELS);
        setContent(buildLayout());
        initBinder();
    }

    @Override
    void initBinder() {
        binder = new Binder<>();
        binder.bind(name, Company::getName, Company::setName);

        // Validation
        binder.forField(name)
                .withValidator(new StringLengthValidator(
                        "Name must be between 1 and 40 characters long",
                        1, 40))
                .bind(Company::getName, Company::setName);
    }

    @Override
    Component buildLayout() {
        FormLayout content = new FormLayout();

        HorizontalLayout nameLayout = new HorizontalLayout();
        nameLayout.setMargin(new MarginInfo(true, false, false, false));
        nameLayout.setSpacing(true);
        name = new TextField("Name:");
        nameLayout.addComponent(name);
        content.addComponent(nameLayout);

        content.addComponent(getActionButtons());
        return content;
    }

    @Override
    void save() {
        if (!validate()) {
            return;
        }

        Company company = generateCompany();
        listener.saveCompany(company);
        close();
        NotificationUtils.show("Company successfully added!", Notification.Type.HUMANIZED_MESSAGE);
    }

    @Override
    boolean validate() {
        BinderValidationStatus<Company> status = binder.validate();
        return status.getValidationErrors().isEmpty();
    }

    private Company generateCompany() {
        Company company = new Company();
        company.setName(name.getValue());
        return company;
    }
}
