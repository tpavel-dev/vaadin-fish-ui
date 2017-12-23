package kz.kcell.apps.pentagon.coda_axon.ws.client;

import kz.kcell.apps.common.security.UsernamePasswordCredential;
import kz.kcell.apps.pentagon.coda.ws.client.AbstractCODAWebServicesClient;
import kz.kcell.apps.pentagon.coda.ws.client.stub.MessagingWS;
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
public class MessagingWebServicesClient extends AbstractCODAWebServicesClient<MessagingWS> {

    @Autowired @Getter @Setter
    private UsernamePasswordCredential credential;


    public MessagingWebServicesClient() {
        super(MessagingWS.class);
    }

    public MessagingWebServicesClient(UsernamePasswordCredential credential) {
        super(credential, MessagingWS.class);
    }

    @Override
    protected String getServiceName() {
        return "messagingService";
    }

    @Override
    protected String getUrl() {
        return "http://kz.kcell.apps.pentagon.coda_axon.ws";
    }

    @Override
    protected String getWsdlUrl() {
       return Config.getMessagingWebServicesWsdlUrl();
    }

//    @Override @Produces @Default
    public MessagingWS getService() throws MalformedURLException {
        return super.getService();
    }

}

