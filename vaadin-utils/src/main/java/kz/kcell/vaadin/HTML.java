package kz.kcell.vaadin;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 04 12 2014
 */
public class HTML {
    public static class input {
        public static enum type {
             button
            ,checkbox
            ,color
            ,date
            ,datetime
            ,datetime_local("datetime-local")
            ,email
            ,file
            ,hidden
            ,image
            ,month
            ,number
            ,password
            ,radio
            ,range
            ,reset
            ,search
            ,submit
            ,tel
            ,text
            ,time
            ,url
            ,week;
            private String value;

            type(){
                this.value = name();
            }

            type(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }
        }
    }
}
