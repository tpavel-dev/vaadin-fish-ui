package kz.kcell.apps.common.resource;

import kz.kcell.apps.common.Language;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */
public interface ResourceBundle {
    String _ru();
    String _kk();
    String _en();

    void setValue(Language language, String value);
//    String getValue(Language language);
    String name();
//    static <T extends Enum<T>> T valueOf(Class<T> enumType, String name);


     default String getValue(Language language) {
        switch (language) {
            case KZ:
            case KK: return _kk();
            case RU: return _ru();
            case EN: return _en();
            default: return _ru();
        }
    }


    default String getKk() {return  null;}
    default String getKz() {return  null;}
    default String getRu() {return  null;}
    default String getEn() {return  null;}

    default void setKk(String val) {}
    default void setKz(String val) {}
    default void setRu(String val) {}
    default void setEn(String val) {}


}
