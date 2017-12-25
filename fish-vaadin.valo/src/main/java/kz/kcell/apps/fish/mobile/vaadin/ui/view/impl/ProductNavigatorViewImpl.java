package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.common.Format;
import kz.kcell.apps.bonus_cmdr.model.BusinessProduct;
import kz.kcell.apps.bonus_cmdr.model.BusinessProductCtgr;
import kz.kcell.apps.bonus_cmdr.model.BusinessProductStateValue;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ProductNavigatorView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 29 10 2014
 */
@Slf4j
@SpringView(name = ViewsCode.name_services)
@UIScope
public class ProductNavigatorViewImpl extends BaseNavigationView implements ProductNavigatorView {
    public static final String VIEW_NAME = "productDesc";

    @Autowired
    @Getter @Setter
    private ProductNavigatorView.Listener listener;

    private Button refreshBtn;

    public ProductNavigatorViewImpl() {
    }

    @Override
    public void translate() {
        setTitle($(product_title));
    }

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }

    @Override
    protected void injectInit() {
        super.injectInit();
        setTitle($(product_title));
        refreshBtn = new Button("refreshBtn");
        refreshBtn.addClickListener(event -> listener.onUpdate(event));
        content.addComponent(refreshBtn);
    }

    @Override
    protected void initIds() {
        super.initIds();
        setId(product_title.name());
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.addStyleName("products-view");
        setMargin(false);
    }

    @Override
    public void showUpdateScreen() {
    }

    @Override
    public void showProductsScreen() {
    }

    @Override
    public void update() {
        List<BusinessProductCtgr> p = listener.fetchProductCtgrList();
//        getSession().setAttribute(SessionAttributes.PRODUCT_CTGR_LIST.name(), p);

        content.removeAllComponents();

/*
        VerticalLayout vl = new VerticalLayout();
        vl.setMargin(true);
        p.forEach(a -> buildCtgrScreen(a, vl));
*/
        verticalImpl(p);
//        accordionImpl(p);
    }

    private void verticalImpl(List<BusinessProductCtgr> productCtgrs) {
//        HorizontalLayout head = headCaption();
//        head.setMargin(true);
//        head.setComponentAlignment(productCaption, Alignment.MIDDLE_RIGHT );
//        head.setComponentAlignment(bonusCaption, Alignment.MIDDLE_LEFT );

        VerticalLayout list = new VerticalLayout();
//        list.setSizeUndefined();
        list.setWidth(Style._100p);
//        list.setWidth(Style._100p);
        list.setMargin(false);
        list.setSpacing(false);

        list.setHeightUndefined();

/*
        content.addComponents(head, list);
        content.setExpandRatio(list, 1);
*/
        content.setHeightUndefined();
        content.setSpacing(false);
        content.addComponent(list);

        if (productCtgrs != null) {
//            productCtgrs.forEach(ctgr -> {
            productCtgrs
                    .stream()
                    .filter(c -> c.getState() == BusinessProductStateValue.ENABLED)
                    .sorted(Comparator.comparingInt(BusinessProductCtgr::getViewOrder))
                    .forEachOrdered(ctgr -> {
                        Label ctgrCaption = new Label();
                        ctgrCaption.setValue(listener.getCtgrTitle(ctgr));
                        ctgrCaption.setWidthUndefined();
                        ctgrCaption.addStyleName(ValoTheme.LABEL_H3);
                        ctgrCaption.setHeight(Style._100p);
                        list.addComponent(ctgrCaption);
                        list.addComponent(headCaption());
                        list.setComponentAlignment(ctgrCaption, Alignment.MIDDLE_CENTER);

                        ctgr.getProducts()
                                .stream()
                                .filter(p -> p.getState() == BusinessProductStateValue.ENABLED)
                                .sorted(Comparator.comparingInt( p -> Optional.ofNullable(p.getViewOrder()).orElse(0)))
                                .forEachOrdered(product -> {
                                    Button productButton = buildFullName(product);
                                    productButton.addClickListener(e -> listener.onServiceSelected(product));
                                    productButton.setWidth(Style._100p);
                                    productButton.setHeight(5, Unit.EM);
                                    productButton.setId(product_productCaption_label.name() + product.getId());
//                                    productButton.setHtmlContentAllowed(false);
                                    ctgrCaption.setValue(listener.getCtgrTitle(ctgr));
/*
                                    switch (SessionManager.getAccount().getLang()) {
                                        case RU:
                                            ctgrCaption.setValue(ctgr.getNameRu());
                                            break;
                                        case KK:
                                            ctgrCaption.setValue(ctgr.getNameKz());
                                            break;
                                    }
*/

                                    Button bonusButton = new Button($(product_bonus_collumn, Format.edFormat.format(product.getBonus())), e -> listener.onServiceSelected(product));

                                    bonusButton.setWidth(6, Unit.EM);
                                    bonusButton.setHeight(Style._100p);
                                    bonusButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
                                    //l.setSizeFull();
                                    HorizontalLayout h = new HorizontalLayout();
                                    h.setSizeFull();
//                                    h.setWidth(Style._100p);
                                    h.setHeightUndefined();
                                    h.setMargin(false);
                                    h.setSpacing(false);
                                    h.addComponents(productButton, bonusButton);
                                    h.setExpandRatio(productButton, 1);
                                    h.setComponentAlignment(bonusButton, Alignment.MIDDLE_LEFT);
                                    h.setStyleName(ValoTheme.LAYOUT_CARD);
                                    list.addComponent(h);
                                });
                    });
        }
    }

    private HorizontalLayout headCaption() {
        HorizontalLayout head = new HorizontalLayout();
        Label clearFix = new Label("");
        Label productCaption = new Label($(product_productCaption_label));
        Label bonusCaption = new Label($(product_bonusCaption_label));
        bonusCaption.setWidth(4, Unit.EM);
        clearFix.setWidth(2, Unit.EM);
        productCaption.addStyleName(ValoTheme.LABEL_SMALL);
        bonusCaption.addStyleName(ValoTheme.LABEL_SMALL);

        head.setSpacing(true);
        head.setWidth(Style._100p);
        head.setHeight(2, Unit.EM);
        head.addComponents(clearFix, productCaption, bonusCaption);
        head.setMargin(new MarginInfo(false, true, false, false));
        head.setExpandRatio(productCaption, 1);
        return head;
    }

    public void accordionImpl(List<BusinessProductCtgr> p) {
        Accordion accordion = new Accordion();
        accordion.setSizeFull();
        if (p != null) {
            p.forEach(a -> buildCtgrScreen(a, accordion));
        }

        content.addComponent(accordion);
    }

    private void buildCtgrScreen(BusinessProductCtgr p, Accordion accordion) {
        VerticalLayout vl = new VerticalLayout();

        accordion.addTab(vl, p.getNameRu());
        p.getProducts().forEach(q -> {
            Button b = buildFullName(q);
            b.addClickListener(e -> listener.onServiceSelected(q));
            b.setWidth(Style._100p);
            b.setHeight(5, Unit.EM);
            b.setId(product_productCaption_label.name() + q.getId());

            Button bonusButton = new Button($(product_bonusButton_label, Format.edFormat.format(q.getBonus()))
                    , e -> listener.onServiceSelected(q));

            bonusButton.setWidth(10, Unit.EM);
            bonusButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
            //l.setSizeFull();
            HorizontalLayout h = new HorizontalLayout();
            h.setSizeFull();
            h.addComponents(b, bonusButton);
            h.setExpandRatio(b, 1);
            h.setComponentAlignment(bonusButton, Alignment.MIDDLE_RIGHT);
            h.setStyleName(ValoTheme.LAYOUT_CARD);
            vl.addComponent(h);
//            vl.setSizeFull();
        });
    }

    private Button buildFullName(BusinessProduct q) {
//        Button b = new NativeButton(listener.getProductName(q));
        Button b = new Button();

//            b.setStyleName(ValoTheme.BUTTON_LINK);
        b.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        b.addStyleName("v-button-multiline");
        b.setHtmlContentAllowed(true);
//        b.setHtmlContentAllowed(false);
        b.setCaptionAsHtml(true);
        b.setCaption(listener.getProductName(q));

        return b;
    }

    private void buildCtgrScreen(BusinessProductCtgr p, AbstractComponentContainer container) {
        Button b = new Button(p.getNameRu());
        container.addComponent(b);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        update();
    }
}