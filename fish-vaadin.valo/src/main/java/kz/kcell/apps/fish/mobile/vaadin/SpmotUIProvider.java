/*
package kz.kcell.apps.spmot.mobile.vaadin;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.spring.server.SpringUIProvider;
import com.vaadin.ui.UI;
import kz.kcell.apps.spmot.mobile.vaadin.controller.IoController;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.impl.ValoMainUIImpl;
import org.springframework.web.context.WebApplicationContext;

public class SpmotUIProvider extends SpringUIProvider {


    public SpmotUIProvider(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

//    @Getter
//    private SpmotMainController controller  = new SpmotMainController();

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        return ValoMainUIImpl.class;
    }

    @Override
    public UI createInstance(UICreateEvent event) {

        IoController ioController = IoController.newSessionScopeInstance();
        ioController.build();
        // todo !!!!!!
//        ioController.getMainController().post(EventType.START_SESSION);
        return ioController.getMainUI();
    }


}*/
