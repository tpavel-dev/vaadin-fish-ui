package kz.kcell.apps.pentagon.coda.ws.client;

import kz.kcell.apps.common.security.UsernamePasswordCredential;
import kz.kcell.apps.common.ws.WSClient;
import lombok.Getter;
import lombok.Setter;

import java.net.MalformedURLException;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
public abstract class AbstractCODAWebServicesClient<T> extends WSClient<T> {

    @Getter
    private final Class<T> clazz;

    @Getter @Setter
    protected UsernamePasswordCredential credential;

    public AbstractCODAWebServicesClient(UsernamePasswordCredential credential, Class<T> clazz) {
        this.credential = credential;
        this.clazz = clazz;
    }

    public AbstractCODAWebServicesClient(Class<T> clazz) {
        this.clazz = clazz;
    }


    public T getService() throws MalformedURLException {
        return getServicePort(
                getWsdlUrl(),
                getUrl(),
                getCredential().getUsername(),
                getCredential().getPassword(),
                getTimeout(),
                clazz,
                getServiceName()
        );
    }

    protected abstract String getServiceName();

    protected abstract String getUrl();

    protected abstract String getWsdlUrl();

//    protected abstract String getPath();

//    protected abstract int getPort();

//    protected abstract String getHost();

//    protected String getProtocol() {
//        return "http";
//    };

    protected int getTimeout() {
        return CODADefaultConfig.DEFAULT_WS_TIMEOUT;
    };
}
