package kz.kcell.apps.common;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 04 03 2015
 */
public class HttpUtils {
    public static void disableCache(HttpServletResponse resp) {
        // Set standard HTTP/1.1 no-cache headers.
        resp.addHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        resp.addHeader("Pragma", "no-cache");

        // Set to expire far in the past. Prevents caching at the proxy server
        resp.addIntHeader(HttpHeaders.EXPIRES, 0);
    }

}
