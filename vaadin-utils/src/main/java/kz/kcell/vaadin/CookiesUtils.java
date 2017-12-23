package kz.kcell.vaadin;

import com.vaadin.server.VaadinService;
import kz.kcell.apps.common.security.Protector;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;

@Slf4j
public class CookiesUtils {

    private static String PASSWORD_COOKIE_NAME = "lsd";
    private static String MSISDN_COOKIE_NAME = "psd";
    private static String SALT = "kasdjv9334kjn34f";
    // 15 days
    private static int PASSWORD_COOKIE_EXPIRE_SEC = 60*60*24*15;


    public static Cookie createCookie(String name, String value) {
        return createCookie(name, value, PASSWORD_COOKIE_EXPIRE_SEC);

    }

    public static Cookie createCookie(String name, String value, int expireSec) {
        log.debug("Create cookie {}:{}='{}'",name, expireSec, value);
        Cookie myCookie = new Cookie(name, value);
        myCookie.setMaxAge(expireSec);
        myCookie.setPath("/");
//        myCookie.setPath(VaadinService.getCurrentRequest().getContextPath());
//        myCookie.setDomain(VaadinService.getCurrentRequest().getRemoteHost());
        return myCookie;
    }

    public static String getValueCookie(String name) {

        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        String value = null;
        for (Cookie cookie:cookies) {
            log.trace("cookie {} {}[{}]={}",cookie.getDomain(), cookie.getName(), cookie.getMaxAge(),  cookie.getValue());
            if(cookie.getName().equals(name)) {
                value = cookie.getValue();
            }
        }
        String result = StringUtils.isNoneEmpty(value)?value.trim():null;
        log.debug("Get cookie {}='{}'",name,result);
        return result;
    }

    public static void resetPasswordInCookie() {
        VaadinService.getCurrentResponse().addCookie(createCookie(PASSWORD_COOKIE_NAME, null, 0));
        VaadinService.getCurrentResponse().addCookie(createCookie(MSISDN_COOKIE_NAME, null, 0));
    }

    public static void setPasswordInCookie(String msisdn, String password) throws Exception {
        password = Protector.encrypt(password, SALT);
        VaadinService.getCurrentResponse().addCookie(createCookie(PASSWORD_COOKIE_NAME, password));
        VaadinService.getCurrentResponse().addCookie(createCookie(MSISDN_COOKIE_NAME, msisdn));
    }

    public static void dumpListCookies() {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        for (Cookie cookie:cookies) {
            log.debug("cookie Domain:'{}' Path:'{}' name:'{}' age:'{}'={}",cookie.getDomain(),cookie.getPath(), cookie.getName(), cookie.getMaxAge(),  cookie.getValue());
        }
    }

    /**
     *
     * @return password if saved in cookie else null
     */
    public static String getPasswordFromCookie() {
        return CookiesUtils.getValueCookie(PASSWORD_COOKIE_NAME);
    }

    public static String getMsisdnFromCookie() {
        return CookiesUtils.getValueCookie(MSISDN_COOKIE_NAME);
    }


    public static boolean hasPasswordInCookie() {
        return getPasswordFromCookie() != null;
    }

    public static String getPasswordEncryptedValueFromCookie() throws Exception {
        return Protector.decrypt(CookiesUtils.getPasswordFromCookie(), SALT);
    }

}
