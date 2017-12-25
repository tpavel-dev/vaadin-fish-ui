package kz.kcell.apps.fish.resources;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.resource.ResourceBundle;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by x on 4/29/17.
 */
public class PropertiesResourceBundle implements ResourceBundle {

    @Getter @Setter private String ru;
    @Getter @Setter private String kk;
    @Getter @Setter private String en;


    @Override public String _ru() {return ru;}

    @Override public String _kk() {return kk;}

    @Override public String _en() {return en;}

    @Override
    public void setValue(Language language, String value) {

    }

    @Override
    public String name() {
        return null;
    }
}
