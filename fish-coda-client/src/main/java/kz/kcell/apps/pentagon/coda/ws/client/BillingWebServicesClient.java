package kz.kcell.apps.pentagon.coda.ws.client;

import kz.kcell.apps.common.security.UsernamePasswordCredential;
import kz.kcell.apps.pentagon.coda.ws.client.stub.BillingServicesWS;
import kz.kcell.apps.spmot.Config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.MalformedURLException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
//@Service
public class BillingWebServicesClient extends AbstractCODAWebServicesClient<BillingServicesWS> {

    @Autowired @Getter @Setter
    private UsernamePasswordCredential credential;

    public BillingWebServicesClient(UsernamePasswordCredential credential) {
        super(credential, BillingServicesWS.class);
    }

    public BillingWebServicesClient() {
        super(BillingServicesWS.class);
    }

    @Override
    protected String getServiceName() {
        return "billingServices";
    }

    @Override
    protected String getUrl() {
        return "http://kz.kcell.apps.pentagon.coda.ws";
    }

    @Override
    protected String getWsdlUrl() {
       return Config.getBillingWebServicesWsdlUrl();
    }

//    @Override @Produces @Default
    public BillingServicesWS getService() throws MalformedURLException {
        return super.getService();
    }

}

