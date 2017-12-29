package kz.kcell.apps.fish.mobile.vaadin.ui.view.component;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import kz.kcell.app.bonus_cmdr.ws.stub.Company;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CompaniesView;

public class CompanyInfo extends AbstractInfo<Company> {

    private CompaniesView.Listener listener;
    private Company company;
    private boolean editable;

    private Panel companyCommonPanel;

    private TextField cid;
    private TextField name;
    private TextField fileName;
    private TextField isCompleted;
    private TextField uploadedItems;

    public CompanyInfo(CompaniesView.Listener listener, Company company, boolean editable) {
        this.listener = listener;
        this.company = company == null ? new Company() : company;
        this.editable = editable;
        init();
    }

    @Override
    protected void init() {
        companyCommonPanel = new Panel("Common info");
        companyCommonPanel.setSizeUndefined();

        cid = new TextField("CID:");
        name = new TextField("Name:");
        fileName = new TextField("File name:");
        isCompleted = new TextField("Completed:");
        uploadedItems = new TextField("Uploaded items:");

        cid.setEnabled(false);
        fileName.setEnabled(false);
        isCompleted.setEnabled(false);
        uploadedItems.setEnabled(false);

        name.setEnabled(editable);

        super.init();
    }

    @Override
    protected void buildLayout() {
        FormLayout commonInfo = new FormLayout();
        commonInfo.addComponent(cid);
        commonInfo.addComponent(name);
        commonInfo.addComponent(fileName);
        commonInfo.addComponent(isCompleted);
        commonInfo.addComponent(uploadedItems);
        commonInfo.setSizeUndefined();
        commonInfo.setMargin(true);
        companyCommonPanel.setContent(commonInfo);

        addComponent(companyCommonPanel);
    }

    @Override
    public void setValue(Company company) {
        this.company = company;
        setFieldsValue();
    }

    @Override
    protected void setFieldsValue() {

        cid.setValue(company.getCid() != null ? String.valueOf(company.getCid()) : "");
        name.setValue(company.getName() != null ? company.getName() : "");
        fileName.setValue(company.getFileName() != null ? company.getFileName() : "");
        isCompleted.setValue(company.getIsComplated() != null ? String.valueOf(company.getIsComplated()) : "");
        uploadedItems.setValue(company.getUploadedItems() != null ? String.valueOf(company.getUploadedItems()) : "");
    }

    @Override
    public Company getBean() {
        Company com = new Company();
        com.setCid(company.getCid());
        com.setName(name.getValue());
        com.setFileName(company.getFileName());
        com.setIsComplated(company.getIsComplated());
        com.setUploadedItems(company.getUploadedItems());

        return com;
    }

}
