package kz.kcell.apps.bonus_cmdr.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */
public class AccessGroupUtils {
    public static boolean checkAccess(String a, AccessGroup b) {
        return checkAccess(a, b.name());
    }

    public static boolean checkAccess(List<String> a, AccessGroup b) {
        return checkAccess(a, b.name());
    }

    public static boolean checkAccess(List<String> a, String b) {
        return checkAccess(a, parse(b));
    }

    public static boolean checkAccess(String a, List<String> b) {
        return checkAccess(parse(a), b);
    }

    public static boolean checkAccess(String a, String b) {
        return checkAccess(parse(a), parse(b));
    }

    public static boolean checkAccess(Collection<String> ac1, Collection<String> ac2) {
        boolean result = false;

        if (ac1 != null && ac2 != null) {
            for (String a1 : ac1) {
                for (String a2 : ac2) {
                    if (a1.equalsIgnoreCase(a2)) {
                        return true;
                    }
                }
            }

        }
        return result;
    }

    public static List<String> parse(String accessGroups) {
        List<String> result = accessGroups == null
                ? Collections.emptyList()
                : Arrays.asList(accessGroups.split("[ ,:;\\t\\n]+"));
        return result;
    }

}
