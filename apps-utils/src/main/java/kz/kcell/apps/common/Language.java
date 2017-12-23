package kz.kcell.apps.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 23 10 2014
 */
@Slf4j
public enum Language {
     RU
    ,EN
    ,KK
    ,KZ;

    public String lowerCase() {
        return name().toLowerCase();
    }

    public static Language fromLocale(Locale locale) {
//        locale.getLanguage()

        switch (locale.getLanguage().toLowerCase()) {
            case "ru": return Language.RU;
            case "kk": return Language.KK;
            case "kz": return Language.KK;
            case "en": return Language.EN;
            default:
                log.error("No detect language for locale {}. Default set RU",locale);
                return Language.RU;
        }

    }
}
