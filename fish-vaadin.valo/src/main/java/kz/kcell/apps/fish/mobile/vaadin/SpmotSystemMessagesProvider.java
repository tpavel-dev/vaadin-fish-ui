//package kz.kcell.apps.spmot.mobile.vaadin;
//
//import com.vaadin.server.CustomizedSystemMessages;
//import com.vaadin.server.SystemMessages;
//import com.vaadin.server.SystemMessagesInfo;
//import com.vaadin.server.SystemMessagesProvider;
//
//import static kz.kcell.apps.spmot.domain.spmot.entity.SpmotResourceBundle.*;
//import static kz.kcell.apps.spmot.mobile.vaadin.SpmotMobileResourceManager.$;
//
///**
// * @author Pavel.Terechshenkov@kcell.kz
// * @since 19 12 2014
// */
//public class SpmotSystemMessagesProvider implements SystemMessagesProvider {
//    @Override
//    public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
//        CustomizedSystemMessages messages = new CustomizedSystemMessages();
////            messages.setCommunicationErrorCaption("Comm Err");
////            messages.setCommunicationErrorMessage("This is bad.");
////            messages.setCommunicationErrorNotificationEnabled(true);
////            messages.setCommunicationErrorURL("http://vaadin.com/");
//
//        messages.setCookiesDisabledMessage($(cookiesDisabledMessage));
//        messages.setCookiesDisabledCaption($(cookiesDisabledCaption));
//
//        messages.setAuthenticationErrorCaption($(authenticationErrorCaption));
//        messages.setAuthenticationErrorMessage($(authenticationErrorMessage));
//
//        messages.setCommunicationErrorCaption($(communicationErrorCaption));
//        messages.setCommunicationErrorMessage($(communicationErrorMessage));
//
//        messages.setInternalErrorCaption($(internalErrorCaption));
//        messages.setInternalErrorMessage($(internalErrorMessage));
//
////        messages.setOutOfSyncCaption(SpmotResourceManager.$(SpmotResourceBundle.outOfSyncCaption));
////        messages.setOutOfSyncMessage(SpmotResourceManager.$(SpmotResourceBundle.outOfSyncMessage));
//
//        messages.setSessionExpiredCaption($(sessionExpiredCaption));
//        messages.setSessionExpiredMessage($(sessionExpiredMessage));
//
//        return messages;
//    }
//}
