package kz.kcell.apps.fish.resources;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.resource.ResourceBundle;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */
@Slf4j
public class ResourceManager {

    public static String $(ResourceBundle resourceBundle, Object... args) {
        if(args != null && args[0] != null && args[0] instanceof Language) {
            throw new IllegalArgumentException("First argument not must be Language");
        }
        return String.format($(Language.RU, resourceBundle, args), args);
    }

    public static String $(Language lang, ResourceBundle resourceBundle, Object... args) {
        return String.format($(lang, resourceBundle ), args);
    }

    public static String $(Language lang, ResourceBundle resourceBundle) {
        switch (lang) {
            case RU: return resourceBundle._ru();
            case KK: return resourceBundle._kk();
            case KZ: return resourceBundle._kk();
            case EN: return resourceBundle._en();
            default: return resourceBundle._ru();
        }
//        return val;
    }



}
