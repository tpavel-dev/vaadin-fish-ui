package kz.kcell.app.bonus_cmdr.ws_api;

import kz.kcell.app.bonus_cmdr.model.Company;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@Service
public interface CompanyService {
    List<Company> getCompanyAll();
    Company getCompany(@WebParam(name = "cid") Long cid);

}
