package kz.kcell.apps.common.restclient.interceptors;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 31 10 2014
 */
public class HeaderValueNotFoundException extends RuntimeException {
    public HeaderValueNotFoundException(String headerName) {
        super("Header not found: "+headerName);
    }
}
