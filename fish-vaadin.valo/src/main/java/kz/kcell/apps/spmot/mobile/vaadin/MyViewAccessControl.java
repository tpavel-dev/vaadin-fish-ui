package kz.kcell.apps.spmot.mobile.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.access.ViewInstanceAccessControl;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import kz.kcell.apps.spmot.mobile.vaadin.controller.SessionManager;

/**
 * Created by x on 6/5/17.
 */
//@SpringComponent
//@UIScope
public class MyViewAccessControl implements ViewAccessControl, ViewInstanceAccessControl{

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
//        if(beanName.equals("loginViewImpl")) return true;
//        if(beanName.equals("errorViewImpl")) return true;
//        if(beanName.equals("logoutViewImpl")) return true;
//        return SessionManager.isAuthorized();
        return true;
    }

    @Override
    public boolean isAccessGranted(UI ui, String beanName, View view) {
        if(beanName.equals("loginViewImpl")) return true;
        if(beanName.equals("errorViewImpl")) return true;
        if(beanName.equals("logoutViewImpl")) return true;
        return SessionManager.isAuthorized();

    }
}
