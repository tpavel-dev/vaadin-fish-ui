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
 * 2017-12-28T15:17:10.678+06:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "AuthServiceWSService", 
                  wsdlLocation = "http://10.10.170.53:8080/services/auth?wsdl",
                  targetNamespace = "http://ws_impl.bonus_cmdr.app.kcell.kz/") 
public class AuthServiceWSService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws_impl.bonus_cmdr.app.kcell.kz/", "AuthServiceWSService");
    public final static QName AuthPort = new QName("http://ws_impl.bonus_cmdr.app.kcell.kz/", "authPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.10.170.53:8080/services/auth?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AuthServiceWSService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.10.170.53:8080/services/auth?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AuthServiceWSService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AuthServiceWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AuthServiceWSService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public AuthServiceWSService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public AuthServiceWSService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public AuthServiceWSService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns AuthService
     */
    @WebEndpoint(name = "authPort")
    public AuthService getAuthPort() {
        return super.getPort(AuthPort, AuthService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthService
     */
    @WebEndpoint(name = "authPort")
    public AuthService getAuthPort(WebServiceFeature... features) {
        return super.getPort(AuthPort, AuthService.class, features);
    }

}
