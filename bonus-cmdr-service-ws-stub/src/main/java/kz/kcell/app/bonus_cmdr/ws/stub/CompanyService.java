package kz.kcell.app.bonus_cmdr.ws.stub;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2017-12-25T17:09:05.069+06:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", name = "CompanyService")
@XmlSeeAlso({ObjectFactory.class})
public interface CompanyService {

    @WebMethod
    @RequestWrapper(localName = "getCompany", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompany")
    @ResponseWrapper(localName = "getCompanyResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompanyResponse")
    @WebResult(name = "return", targetNamespace = "")
    public kz.kcell.app.bonus_cmdr.ws.stub.Company getCompany(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid
    );

    @WebMethod
    @RequestWrapper(localName = "getCompanyAll", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompanyAll")
    @ResponseWrapper(localName = "getCompanyAllResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompanyAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<kz.kcell.app.bonus_cmdr.ws.stub.Company> getCompanyAll();

    @WebMethod
    @RequestWrapper(localName = "getBonusParams", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetBonusParams")
    @ResponseWrapper(localName = "getBonusParamsResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetBonusParamsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<kz.kcell.app.bonus_cmdr.ws.stub.BonusParams> getBonusParams(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid
    );
}
