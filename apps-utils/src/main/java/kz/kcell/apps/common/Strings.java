package kz.kcell.apps.common;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
public class Strings {

    public static String onlyNumbers(String str) {

        char[] buffer = new char[str.length()];

        int i=0;
        for(char ch:str.toCharArray()) {
            if('0' <= ch && ch <= '9') {
                buffer[i] = ch;
                i++;
            }
        }

        return String.copyValueOf(buffer, 0, i);
    }

}
