package kz.kcell.apps.fish.mobile.vaadin.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by x on 6/6/17.
 */
@SpringViewDisplay
@Slf4j
public class MainViewDisplay extends CssLayout implements ViewDisplay {

    @Override
    public void showView(View view) {
        if (view instanceof Component == false) {
            throw new IllegalArgumentException(
                    "View is not a component: " + view);
        }

        this.removeAllComponents();
        this.addComponent((Component) view);
    }
}
