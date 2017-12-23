package kz.kcell.apps.spmot.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.ProductNavigatorView;
import kz.kcell.apps.spmot.mobile.vaadin.ui.view.ViewsCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 11 2014
 */
@SpringView(name = ViewsCode.name_productDesc)
public class ProductDescViewImpl extends BaseNavigationView {
    private Button backButton;
    private BrowserFrame frame;

    @Autowired
    @Getter
    private ProductNavigatorView.Listener listener;

    private Label iframeLabel;

    public ProductDescViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setProductDescView(this);
        super.init();
    }


    @Override
    protected void initIds() {
        super.initIds();
    }

    @Override
    public void translate() {
        super.translate();
    }

    @Override
    protected void injectInit() {
        setTitle("");
        backButton = new Button("<< Back", e -> {listener.backFromProductDesc();});
        frame = new BrowserFrame("");
        iframeLabel = new Label("", ContentMode.HTML);
//        frame = new BrowserFrame("",new ExternalResource("http://habrahabr.ru"));
    }

    @Override
    protected void buildLayout() {
        buildLayoutIframe();
    }

    protected void buildLayoutIframe() {
        super.buildLayout();
        VerticalLayout v = new VerticalLayout();
        v.addComponent(iframeLabel);
        v.addComponent(backButton);
        v.setSizeFull();
        v.setExpandRatio(iframeLabel, 1);
        iframeLabel.setSizeFull();
        content.removeAllComponents();
        content.addComponent(v);
        content.setExpandRatio(v, 1);
        content.setMargin(false);
//        content.setMargin(true);
    }

    protected void buildLayoutBrowserFrame() {
        super.buildLayout();
        VerticalLayout v = new VerticalLayout();
        v.addComponent(frame);
        v.addComponent(backButton);
        v.setSizeFull();
        v.setExpandRatio(frame, 1);
        frame.setSizeFull();
        content.removeAllComponents();
        content.addComponent(v);
        content.setExpandRatio(v, 1);
        content.setMargin(false);
//        content.setMargin(true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        listener.enterProductDescView(this);
    }

    public void setProductDescUrl(String name) {
        frame.setSource(new ExternalResource(name));
//        iframeLabel.setValue("<iframe sandbox='' src='"+name+"' height='100%' width='100%'></iframe>");
        iframeLabel.setValue("<iframe src='"+name+"' height='100%' width='100%'></iframe>");
//        iframeLabel.setValue("<iframe src='http://habrahabr.ru' height='100%' width='100%'></iframe>");
    }
}