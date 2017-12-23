package kz.kcell.apps.pentagon.coda.ws.client;

import kz.kcell.apps.pentagon.coda.ws.client.AbstractCODAWebServicesClient;
import kz.kcell.apps.pentagon.coda.ws.client.stub.RefBooksServicesWS;
import kz.kcell.apps.common.security.BaseUsernamePasswordCredential;
import kz.kcell.apps.spmot.Config;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
//@Service
public class RefBooksWebServicesClient extends AbstractCODAWebServicesClient<RefBooksServicesWS> {

    public RefBooksWebServicesClient(BaseUsernamePasswordCredential credential) {
        super(credential, RefBooksServicesWS.class);
    }

    @Override
    protected String getServiceName() {
        return "rbServices";
    }

    @Override
    protected String getUrl() {
        return "http://kz.kcell.apps.pentagon.coda.ws";
    }

    @Override
    protected String getWsdlUrl() {
        return Config.getRefBooksWebServicesWsdlUrl();
    }

}

