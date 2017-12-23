package kz.kcell.apps.pentagon.coda.ws.client;

import kz.kcell.apps.common.security.BaseUsernamePasswordCredential;
import kz.kcell.apps.common.security.UsernamePasswordCredential;
import kz.kcell.apps.pentagon.coda.ws.client.stub.CustomerServicesWS;
import kz.kcell.apps.pentagon.coda.ws.client.stub.UserServicesWS;
import kz.kcell.apps.spmot.Config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
//@Service
public class CustomersWebServiceClient extends AbstractCODAWebServicesClient<CustomerServicesWS> {

    @Autowired @Getter @Setter
    private UsernamePasswordCredential credential;

    public CustomersWebServiceClient(UsernamePasswordCredential credential) {
        super(credential, CustomerServicesWS.class);
    }

    public CustomersWebServiceClient() {
        super(CustomerServicesWS.class);
    }

    @Override
    protected String getServiceName() {
        return "customerServices";
    }

    @Override
    protected String getUrl() {
        return "http://kz.kcell.apps.pentagon.coda.ws";
    }

    @Override
    protected String getWsdlUrl() {
        return Config.getCustomersWebServiceWsdlUrl();
    }

    @Override @Bean
    public CustomerServicesWS getService() throws MalformedURLException {
        return super.getService();
    }
}
