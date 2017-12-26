package kz.kcell.app.bonus_cmdr.ws.stub;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2017-12-25T17:09:05.074+06:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "CompanyServiceWSService", 
                  wsdlLocation = "http://10.10.170.53:8080/services/company?wsdl",
                  targetNamespace = "http://ws_impl.bonus_cmdr.app.kcell.kz/") 
public class CompanyServiceWSService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws_impl.bonus_cmdr.app.kcell.kz/", "CompanyServiceWSService");
    public final static QName CompanyPort = new QName("http://ws_impl.bonus_cmdr.app.kcell.kz/", "companyPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.10.170.53:8080/services/company?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CompanyServiceWSService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.10.170.53:8080/services/company?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CompanyServiceWSService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CompanyServiceWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CompanyServiceWSService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public CompanyServiceWSService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public CompanyServiceWSService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public CompanyServiceWSService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns CompanyService
     */
    @WebEndpoint(name = "companyPort")
    public CompanyService getCompanyPort() {
        return super.getPort(CompanyPort, CompanyService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CompanyService
     */
    @WebEndpoint(name = "companyPort")
    public CompanyService getCompanyPort(WebServiceFeature... features) {
        return super.getPort(CompanyPort, CompanyService.class, features);
    }

}
