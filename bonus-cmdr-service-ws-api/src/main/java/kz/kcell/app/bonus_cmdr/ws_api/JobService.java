package kz.kcell.app.bonus_cmdr.ws_api;

import kz.kcell.app.bonus_cmdr.model.Company;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface JobService {

    Company startCompany(@WebParam(name = "cid") Long cid);

}
