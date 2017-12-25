package kz.kcell.app.bonus_cmdr.ws_api.impl;

import kz.kcell.app.bonus_cmdr.model.Company;
import kz.kcell.app.bonus_cmdr.ws_api.CompanyService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Override
    public List<Company> getCompanyAll() {
        return generateCompanies();
    }

    @Override
    public Company getCompany(Long cid) {
        return new Company();
    }

    private List<Company> generateCompanies() {
        List<Company> companies = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Company company = new Company();
            company.setCid(i * 13l);
            company.setName("Company " + company.getCid());
            companies.add(company);
        }
        return companies;
    }
}
