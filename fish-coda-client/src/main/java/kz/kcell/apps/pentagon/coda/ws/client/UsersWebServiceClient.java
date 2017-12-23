package kz.kcell.apps.pentagon.coda.ws.client;

import kz.kcell.apps.common.security.BaseUsernamePasswordCredential;
import kz.kcell.apps.pentagon.coda.ws.client.AbstractCODAWebServicesClient;
import kz.kcell.apps.pentagon.coda.ws.client.stub.UserServicesWS;
import kz.kcell.apps.spmot.Config;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
//@Service
public class UsersWebServiceClient extends AbstractCODAWebServicesClient<UserServicesWS> {

    public UsersWebServiceClient(BaseUsernamePasswordCredential credential) {
        super(credential, UserServicesWS.class);
    }

    @Override
    protected String getServiceName() {
        return "userServices";
    }

    @Override
    protected String getUrl() {
        return "http://kz.kcell.apps.pentagon.coda.ws";
    }

    @Override
    protected String getWsdlUrl() {
        return Config.getUsersWebServiceWsdlUrl();
    }


}
