package kz.kcell.vaadin;

import com.vaadin.server.Page;
import lombok.NonNull;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 04 12 2014
 */
public class HackUtils {
    public static void setInputType(@NonNull String id, @NonNull HTML.input.type inputType) {
        Page.getCurrent().getJavaScript().execute("document.getElementById('"+id+"').type='"+inputType+"'");
    }
}
