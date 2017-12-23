package kz.kcell.apps.spmot.mobile.vaadin;

import com.vaadin.server.*;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.Writer;

import static kz.kcell.apps.spmot.domain.spmot.entity.SpmotResourceBundle.unsupportedBrowser;
import static kz.kcell.apps.spmot.mobile.vaadin.SpmotMobileResourceManager.$;


@Slf4j
public class SpmotUnsupportedBrowserHandler extends UnsupportedBrowserHandler {

    /** Cookie used to ignore browser checks */
    public static final String FORCE_LOAD_COOKIE = "vaadinforceload=1";
    private final boolean pass_to_next_handler = false;

    @Override
    public boolean synchronizedHandleRequest(VaadinSession session,
                                             VaadinRequest request, VaadinResponse response) throws IOException {



        // Check if the browser is supported
        // If Chrome Frame is available we'll assume it's ok
        Page currentPage = Page.getCurrent();
        if(currentPage == null) return pass_to_next_handler;

//        WebBrowser browser = currentPage.getWebBrowser();
//        if(browser == null) return pass_to_next_handler;

        WebBrowser browser = Page.getCurrent().getWebBrowser();

        // code
        if (!isSupportedBrowser(browser, request) || browser.isTooOldToFunctionProperly() && !browser.isChromeFrameCapable()) {
            // bypass if cookie set
            String c = request.getHeader("Cookie");
            if (c == null || !c.contains(FORCE_LOAD_COOKIE)) {
                writeBrowserTooOldPage(request, response);
                return true; // request handled
            }
        }

        return false; // pass to next handler
    }

    private boolean isSupportedBrowser(WebBrowser browser, VaadinRequest request) {
        boolean result = false;
        result =   browser.isChrome()
                || browser.isSafari()
                || browser.isFirefox()
//                || browser.isOpera()
                   ;
        if(result == false) {
            log.info(
                "Unsupported browser with user-agent {}"
               ,request.getHeader(HttpHeaders.USER_AGENT)
            );
        }
        return result;
    }

    @Override
    protected void writeBrowserTooOldPage(com.vaadin.server.VaadinRequest request
                , com.vaadin.server.VaadinResponse response) throws IOException
    {
        //super.writeBrowserTooOldPage(request, response);
        //if(true) return;

        response.setContentType("text/html; charset=utf-8");
        Writer pageWriter = response.getWriter();

//        response.setHeader("UTF-8");
//        response.set

        pageWriter.write($(unsupportedBrowser, FORCE_LOAD_COOKIE));
        pageWriter.close();
    };
}
