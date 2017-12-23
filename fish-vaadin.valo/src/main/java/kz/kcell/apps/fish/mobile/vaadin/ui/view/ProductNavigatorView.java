package kz.kcell.apps.fish.mobile.vaadin.ui.view;


import kz.kcell.apps.common.exceptions.BaseException;
import kz.kcell.apps.fish.domain.spmot.entity.BusinessProduct;
import kz.kcell.apps.fish.domain.spmot.entity.BusinessProductCtgr;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.ProductDescViewImpl;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.OrderIvrFormViewImpl;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.impl.OrderIvrViewImpl;
import kz.kcell.vaadin.ui.View;
import kz.kcell.vaadin.ui.behaviors.SubmitBehavior;

import java.util.EventObject;
import java.util.List;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 17 09 2014
 */
public interface ProductNavigatorView extends View<ProductNavigatorView.Listener> {

    void showUpdateScreen();

     void showProductsScreen();

    void update();

    void setListener(Listener listener);

    interface Listener extends SubmitBehavior {
        List<BusinessProductCtgr> fetchProductCtgrList();
        void onUpdate(EventObject source);

        void showDescriptionInBrowser();
        void showDescriptionInner();

        void onServiceSelected(BusinessProduct q);

        void onSubscribe() throws BaseException;
        void enterOrderIvr(OrderIvrViewImpl components);
        void enterOrderForm(OrderIvrFormViewImpl components);

        void enterProductDescView(ProductDescViewImpl components);

        String getProductName(BusinessProduct q);
        String getCtgrTitle(BusinessProductCtgr q);

        void backFromProductDesc();

        void setOrderFormView(OrderIvrFormViewImpl components);
        void setOrderIvrView(OrderIvrViewImpl components);
        void setView(ProductNavigatorView view);
        void setProductDescView(ProductDescViewImpl view);

    }
}
