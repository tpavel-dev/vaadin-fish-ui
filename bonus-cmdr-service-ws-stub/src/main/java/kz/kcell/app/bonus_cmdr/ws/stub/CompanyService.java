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
 * 2017-12-29T12:06:48.557+06:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", name = "CompanyService")
@XmlSeeAlso({ObjectFactory.class})
public interface CompanyService {

    @WebMethod
    @RequestWrapper(localName = "clearCompany", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.ClearCompany")
    @ResponseWrapper(localName = "clearCompanyResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.ClearCompanyResponse")
    public void clearCompany(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid
    );

    @WebMethod
    @RequestWrapper(localName = "updateCompany", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.UpdateCompany")
    @ResponseWrapper(localName = "updateCompanyResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.UpdateCompanyResponse")
    public void updateCompany(
        @WebParam(name = "company", targetNamespace = "")
        kz.kcell.app.bonus_cmdr.ws.stub.Company company
    );

    @WebMethod
    @RequestWrapper(localName = "buildCompanyStatus", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.BuildCompanyStatus")
    @ResponseWrapper(localName = "buildCompanyStatusResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.BuildCompanyStatusResponse")
    public void buildCompanyStatus(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid
    );

    @WebMethod
    @RequestWrapper(localName = "getAssigmentState", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetAssigmentState")
    @ResponseWrapper(localName = "getAssigmentStateResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetAssigmentStateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public kz.kcell.app.bonus_cmdr.ws.stub.BonusAssigmentState getAssigmentState(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid,
        @WebParam(name = "bid", targetNamespace = "")
        java.lang.Long bid
    );

    @WebMethod
    @RequestWrapper(localName = "jobInfo", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.JobInfo")
    @ResponseWrapper(localName = "jobInfoResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.JobInfoResponse")
    public void jobInfo();

    @WebMethod
    @RequestWrapper(localName = "getCompanyAll", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompanyAll")
    @ResponseWrapper(localName = "getCompanyAllResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompanyAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<kz.kcell.app.bonus_cmdr.ws.stub.Company> getCompanyAll();

    @WebMethod
    @RequestWrapper(localName = "startAssigmentBonus", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.StartAssigmentBonus")
    @ResponseWrapper(localName = "startAssigmentBonusResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.StartAssigmentBonusResponse")
    public void startAssigmentBonus(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid,
        @WebParam(name = "bid", targetNamespace = "")
        java.lang.Long bid
    );

    @WebMethod
    @RequestWrapper(localName = "updateBonus", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.UpdateBonus")
    @ResponseWrapper(localName = "updateBonusResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.UpdateBonusResponse")
    public void updateBonus(
        @WebParam(name = "bonus", targetNamespace = "")
        kz.kcell.app.bonus_cmdr.ws.stub.BonusParams bonus
    );

    @WebMethod
    @RequestWrapper(localName = "getBonusParams", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetBonusParams")
    @ResponseWrapper(localName = "getBonusParamsResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetBonusParamsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<kz.kcell.app.bonus_cmdr.ws.stub.BonusParams> getBonusParams(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid
    );

    @WebMethod
    @RequestWrapper(localName = "addBonus", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.AddBonus")
    @ResponseWrapper(localName = "addBonusResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.AddBonusResponse")
    public void addBonus(
        @WebParam(name = "bonus", targetNamespace = "")
        kz.kcell.app.bonus_cmdr.ws.stub.BonusParams bonus
    );

    @WebMethod
    @RequestWrapper(localName = "stopAssigmentBonus", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.StopAssigmentBonus")
    @ResponseWrapper(localName = "stopAssigmentBonusResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.StopAssigmentBonusResponse")
    public void stopAssigmentBonus(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid,
        @WebParam(name = "bid", targetNamespace = "")
        java.lang.Long bid
    );

    @WebMethod
    @RequestWrapper(localName = "addCompany", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.AddCompany")
    @ResponseWrapper(localName = "addCompanyResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.AddCompanyResponse")
    public void addCompany(
        @WebParam(name = "company", targetNamespace = "")
        kz.kcell.app.bonus_cmdr.ws.stub.Company company
    );

    @WebMethod
    @RequestWrapper(localName = "getListFile", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetListFile")
    @ResponseWrapper(localName = "getListFileResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetListFileResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<java.lang.String> getListFile();

    @WebMethod
    @RequestWrapper(localName = "removeBonus", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.RemoveBonus")
    @ResponseWrapper(localName = "removeBonusResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.RemoveBonusResponse")
    public void removeBonus(
        @WebParam(name = "bonus", targetNamespace = "")
        kz.kcell.app.bonus_cmdr.ws.stub.BonusParams bonus
    );

    @WebMethod
    @RequestWrapper(localName = "getCompany", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompany")
    @ResponseWrapper(localName = "getCompanyResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.GetCompanyResponse")
    @WebResult(name = "return", targetNamespace = "")
    public kz.kcell.app.bonus_cmdr.ws.stub.Company getCompany(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid
    );

    @WebMethod
    @RequestWrapper(localName = "removeCompany", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.RemoveCompany")
    @ResponseWrapper(localName = "removeCompanyResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.RemoveCompanyResponse")
    public void removeCompany(
        @WebParam(name = "company", targetNamespace = "")
        kz.kcell.app.bonus_cmdr.ws.stub.Company company
    );

    @WebMethod
    @RequestWrapper(localName = "updloadFile", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.UpdloadFile")
    @ResponseWrapper(localName = "updloadFileResponse", targetNamespace = "http://ws_api.bonus_cmdr.app.kcell.kz/", className = "kz.kcell.app.bonus_cmdr.ws.stub.UpdloadFileResponse")
    public void updloadFile(
        @WebParam(name = "cid", targetNamespace = "")
        java.lang.Long cid,
        @WebParam(name = "fileName", targetNamespace = "")
        java.lang.String fileName
    );
}
