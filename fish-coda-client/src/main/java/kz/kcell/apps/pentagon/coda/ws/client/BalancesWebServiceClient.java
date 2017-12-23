package kz.kcell.apps.pentagon.coda.ws.client;

import kz.kcell.apps.common.security.UsernamePasswordCredential;
import kz.kcell.apps.pentagon.coda.ws.client.stub.BalancesServicesWS;
import kz.kcell.apps.pentagon.coda.ws.client.stub.CustomerServicesWS;
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
@Service
public class BalancesWebServiceClient extends AbstractCODAWebServicesClient<BalancesServicesWS> {

    @Autowired @Getter @Setter
    private UsernamePasswordCredential credential;

    public BalancesWebServiceClient(UsernamePasswordCredential credential) {
        super(credential, BalancesServicesWS.class);
    }

    public BalancesWebServiceClient() {
        super(BalancesServicesWS.class);
    }

    @Override
    protected String getServiceName() {
        return "balances";
    }

    @Override
    protected String getUrl() {
        return "http://kz.kcell.apps.pentagon.coda.ws";
    }

    @Override
    protected String getWsdlUrl() {
        return Config.getBalancesWebServiceWsdlUrl();
    }

    @Override @Bean
    public BalancesServicesWS getService() throws MalformedURLException {
        return super.getService();
    }
}
