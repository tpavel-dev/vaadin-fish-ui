package kz.kcell.apps.common.ws;

import javax.xml.ws.BindingProvider;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 12 09 2014
 */
public class WebServiceBinder {
    public static void addUserCredentialsToPort(BindingProvider provider, String username, String password) {
        provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
        provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
    }

    public static void setWSTimeout(BindingProvider provider, long responseTime) {
        provider.getRequestContext().remove("org.jboss.ws.timeout");
        provider.getRequestContext().remove("javax.xml.ws.client.receiveTimeout");
        provider.getRequestContext().put("org.jboss.ws.timeout", Long.toString(responseTime));
        provider.getRequestContext().put("javax.xml.ws.client.receiveTimeout", Long.toString(responseTime));
    }
}
