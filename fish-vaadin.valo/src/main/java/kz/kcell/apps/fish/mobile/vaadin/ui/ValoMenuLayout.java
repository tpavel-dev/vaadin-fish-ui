package kz.kcell.apps.fish.mobile.vaadin.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * 
 * @since
 * @author Vaadin Ltd
 */
@SpringComponent
@UIScope
public class ValoMenuLayout extends HorizontalLayout {

//    CssLayout contentArea = new CssLayout();

    @Autowired
    MainViewDisplay contentArea;

    CssLayout menuArea = new CssLayout();

    public ValoMenuLayout() {
    }

    @PostConstruct
    public void init() {
        setSizeFull();
//        setMargin(false);

        menuArea.setPrimaryStyleName("valo-menu");

        contentArea.setPrimaryStyleName("valo-content");
        contentArea.addStyleName("v-scrollable");
        contentArea.setSizeFull();
        setSpacing(false);

        addComponents(menuArea, contentArea);
        setExpandRatio(contentArea, 1);

    }

    public ComponentContainer getContentContainer() {
        return contentArea;
    }

    public void addMenu(Component menu) {
        menu.addStyleName("valo-menu-part");
        menuArea.addComponent(menu);
    }

    public void hideMainMenu() {
        menuArea.setVisible(false);
    }

    public void showMainMenu() {
        menuArea.setVisible(true);
    }


}