package kz.kcell.apps.common.ws;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * WebService Client
 * @param <T>
 *
 * @author Alexey.Brodovshuk@kcell.kz
 * @author Pavel.Terechshenkov@kcell.kz
 */

public abstract class WSClient<T> {

    protected T getServicePort(String host, int port, String protocol, String wsdl,
                               String targetNamespace, String username, String pass,
                               int timeout, Class clazz)
            throws MalformedURLException {

       return getServicePort(host, port, protocol, wsdl, targetNamespace, username, pass, timeout, clazz
               , clazz.getSimpleName());
    }

    private T getServicePort(String host, int port, String protocol, String wsdl,
                               String targetNamespace, String username, String pass,
                               int timeout, Class clazz, String serviceNameLocalPart)
            throws MalformedURLException {

        return getServicePort(protocol + "://" + host + ":" + port, wsdl, targetNamespace, username, pass, timeout, clazz, serviceNameLocalPart);

    }

    private T getServicePort(String url, String wsdl,
                             String targetNamespace, String username, String pass,
                             int timeout, Class clazz, String serviceNameLocalPart)
            throws MalformedURLException {

        return getServicePort(url + wsdl, targetNamespace, username, pass, timeout, clazz, serviceNameLocalPart);

    }

    @SuppressWarnings("unchecked")
    protected T getServicePort(String wsdlurl,
                               String targetNamespace, String username, String pass,
                               int timeout, Class clazz, String serviceNameLocalPart)
            throws MalformedURLException {
        URL wsdlURL = new URL(wsdlurl);
        QName serviceName = new QName(targetNamespace, serviceNameLocalPart);
        Service service = Service.create(wsdlURL, serviceName);
        T servicePort = service.getPort((Class<T>) clazz);

        addUserCredentialsToPort(servicePort, username, pass);
        setWSTimeout(servicePort, timeout);

        return servicePort;
    }

    protected void addUserCredentialsToPort(Object servicePort, String username, String password) {
        BindingProvider bp = (BindingProvider) servicePort;
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
    }

    protected void setWSTimeout(T servicePort, int responseTime) {
        BindingProvider bp = (BindingProvider) servicePort;

        bp.getRequestContext().remove("org.jboss.ws.timeout");
        bp.getRequestContext().remove("javax.xml.ws.client.receiveTimeout");

        bp.getRequestContext().put("org.jboss.ws.timeout", Integer.toString(responseTime));
        bp.getRequestContext().put("javax.xml.ws.client.receiveTimeout", Integer.toString(responseTime));
    }
}