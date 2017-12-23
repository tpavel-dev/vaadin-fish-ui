package kz.kcell.apps.pentagon.coda_axon.ws.client;

import kz.kcell.apps.common.security.BaseUsernamePasswordCredential;
import kz.kcell.apps.pentagon.coda.ws.client.AbstractCODAWebServicesClient;
import kz.kcell.apps.pentagon.coda.ws.client.stub.BossServicesWS;
import kz.kcell.apps.spmot.Config;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
//@Service
public class BossWebServicesClient extends AbstractCODAWebServicesClient<BossServicesWS> {

    public BossWebServicesClient(BaseUsernamePasswordCredential credential) {
        super(credential, BossServicesWS.class);
    }

    @Override
    protected String getServiceName() {
        return "bossServices";
    }

    @Override
    protected String getUrl() {
        return "http://kz.kcell.apps.pentagon.coda_axon.ws";
    }

    @Override
    protected String getWsdlUrl() {
        return Config.getBossWebServicesWsdlUrl();
    }

}

