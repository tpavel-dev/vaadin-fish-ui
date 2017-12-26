package kz.kcell.apps.fish.mobile.vaadin.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.kcell.apps.common.Format;
import kz.kcell.apps.bonus_cmdr.model.Subscribe;
import kz.kcell.apps.fish.mobile.vaadin.controller.SessionManager;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.SubscribeLogView;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.ViewsCode;
import kz.kcell.vaadin.HTML;
import kz.kcell.vaadin.HackUtils;
import kz.kcell.vaadin.ui.ClearFix;
import kz.kcell.vaadin.ui.Style;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
@Slf4j
@SpringView(name = ViewsCode.name_log)
public class SubscribeLogViewImpl extends BaseNavigationView implements SubscribeLogView {

    private static final int TABLE_COLUM_HEIGHT_EM = 5;

    private boolean new_impl = true;

    @Autowired
    @Getter
    private SubscribeLogView.Listener listener;

    private Grid<Subscribe> table;

    @Getter
    private DateField to;

    // TODO drop
//    private TextFieldUtil toMoble = new TextFieldUtil();
//    private TextFieldUtil fromMobile  = new TextFieldUtil();
    @Getter
    private DateField from;

    @Getter
    private Button submit;

    private VerticalLayout vlist;

    public SubscribeLogViewImpl() {
    }

    @PostConstruct
    public void init() {
        listener.setView(this);
        super.init();
    }

    @Override
    public void translate() {

    }

    @Override
    protected void injectInit() {
        setTitle($(subscribe_log_titile));

        from = new DateField(/*"C"*/);
        to = new DateField(/*"По"*/);

        from.addValueChangeListener(e -> {
            listener.changeFrom(e);
        });
        to.addValueChangeListener(e -> {
            listener.changeTo(e);
        });

        submit = new Button(FontAwesome.REFRESH);
//        submit.setCaption("Обновить");
        submit.addClickListener(e -> {
            updateTableData();
        });
        submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
        submit.focus();
    }

    @Override
    protected void initIds() {
        super.initIds();
        from.setId(subscribe_log_from.name());
        to.setId(subscribe_log_to.name());
        submit.setId(subscribe_log_submit.name());

        // todo drop
//        fromMobile.setId(SpmotResourceBundle.subscribe_log_from.name() + "1");
//        toMoble.setId(SpmotResourceBundle.subscribe_log_to.name() + "1");
    }

    private void hackInputType() {
        HackUtils.setInputType(subscribe_log_from.name(), HTML.input.type.date);
        HackUtils.setInputType(subscribe_log_to.name(), HTML.input.type.date);

//        HackUtils.setInputType(subscribe_log_from.name()+"1", HTML.input.type.date);
//        HackUtils.setInputType(subscribe_log_to.name()+"1", HTML.input.type.date);
    }

    @Override
    protected void buildLayout() {
        super.buildLayout();
        content.removeAllComponents();
//        content.setMargin(new MarginInfo(false, false, true, false));
//        content.setSpacing(false);
//        content.setSizeUndefined();
//        setWidth(Style._100p);
        content.setSizeFull();
        content.setMargin(false);
        HorizontalLayout hg = new HorizontalLayout();
//        Responsive.makeResponsive(hg);
//        hg.addStyleName("wrapping");
        from.setWidth(8, Unit.EM);
        to.setWidth(8, Unit.EM);
        hg.setSpacing(true);
//        hg.setMargin(true);
//        hg.setWidth(Style._100p);
//        hg.setMargin(new MarginInfo(true, false, true, true));
//        hg.setMargin(true);
        submit.setWidth(2, Unit.EM);
        Label clearFix = new Label(" ");
        clearFix.setWidth(0.2f, Unit.EM);
        clearFix.setHeight(3.2f, Unit.EM);
        hg.addComponents(clearFix, from, to, submit);
        hg.setComponentAlignment(from, Alignment.MIDDLE_CENTER);
        hg.setComponentAlignment(to, Alignment.MIDDLE_CENTER);
        hg.setComponentAlignment(submit, Alignment.MIDDLE_CENTER);
        submit.setVisible(false);
//        hg.addComponent(to);
//        hg.addComponent(submit);
//        hg.setComponentAlignment(submit, Alignment.BOTTOM_LEFT);
//        content.removeAllComponents();
        content.addComponent(hg);

        // todo drop
//        hg = new HorizontalLayout(toMoble, fromMobile);
//        content.addComponent(hg);

        if (new_impl) {
            verticalImpl();
        } else {
            tableImpl();
        }
    }

    private void verticalImpl() {
        vlist = new VerticalLayout();
        vlist.setHeightUndefined();
        vlist.setWidth(Style._100p);
        vlist.setSpacing(false);
        vlist.setMargin(false);

        content.addComponent(vlist);
        content.setHeightUndefined();
        content.setWidth(Style._100p);
    }

    private void tableImpl() {
        content.addComponent(getTable());
        getTable().setSizeFull();
        content.setExpandRatio(getTable(), 1);
    }

    //@Override
    protected void buildLayout3() {
        super.buildLayout();
        content.setMargin(false);
//        content.setSpacing(false);
        content.setSizeUndefined();
        setWidth(Style._100p);
        HorizontalLayout hg = new HorizontalLayout();
        Responsive.makeResponsive(hg);
//        hg.addStyleName("wrapping");
        from.setWidth(8, Unit.EM);
        to.setWidth(8, Unit.EM);
        hg.setSpacing(true);
        hg.addComponents(from, to, submit);
//        hg.addComponent(to);
//        hg.addComponent(submit);
        hg.setComponentAlignment(submit, Alignment.BOTTOM_LEFT);
        content.addComponents(hg, getTable());
    }

    //    @Override
    protected void buildLayout2() {
        super.buildLayout();
        setSpacing(false);
        setSizeUndefined();
        setWidth(Style._100p);
        VerticalLayout hg = new VerticalLayout();
        Responsive.makeResponsive(hg);
//        hg.addStyleName("wrapping");
        hg.setSpacing(true);
        hg.addComponent(from);
        hg.addComponent(to);
        hg.addComponent(submit);
        hg.setComponentAlignment(submit, Alignment.BOTTOM_LEFT);
        content.addComponent(hg);
        content.addComponent(getTable());
    }

    //    @Override
    protected void buildLayout1() {
        super.buildLayout();
        HorizontalLayout hg = new HorizontalLayout();
        Responsive.makeResponsive(hg);
//        hg.addStyleName("wrapping");
        hg.setSpacing(true);
        hg.addComponent(from);
        hg.addComponent(to);
        hg.addComponent(submit);
        hg.setComponentAlignment(submit, Alignment.BOTTOM_LEFT);
        hg.setMargin(new MarginInfo(false, false, true, true));
        content.addComponent(hg);
        content.addComponent(getTable());
    }

    @Override
    public void updateTableData() {
        if (listener != null) {
            LocalDate locatFrom = from.getValue() == null
                    ? null : from.getValue();


            LocalDate locatTo = to.getValue() == null
                    ? null : to.getValue();

            Collection<Subscribe> subscribes = listener.fetchLog(locatFrom, locatTo);

            if (new_impl) {
                updateVerticalImpl(subscribes);
            } else {
                updateTableImpl(subscribes);
            }
        }
    }

    private void updateVerticalImpl(Collection<Subscribe> subscribes) {
        vlist.removeAllComponents();
        HorizontalLayout caption = headCaption();
        vlist.addComponent(caption);
//        if(true) return;
        subscribes.forEach(s -> {
            String date = s.getStartDate().toLocalDateTime().format(Format.dateTimeFormat);
            String name = "";

            if (s.getBusinessProductName() != null) {
                switch (SessionManager.getAccount().getLang()) {
                    case RU:
                        name = s.getBusinessProductName().getNameRu();
                        break;
                    case KK:
                        name = s.getBusinessProductName().getNameKz();
                        break;
                }
            } else {
                name = "PRODUCT NOT FOUND";
                log.error("Product name not found: " + s.dump());
            }


//            String prodText = date + "\n" + name;
            String prodText = date + "<br />" + name;
//            Label productButton = new Label(date + "<br />" + name, ContentMode.HTML);
            Label productButton = new Label(prodText, ContentMode.HTML);
            productButton.setWidth(Style._100p);
            productButton.setHeight(7, Unit.EM);

            Label bonusButton = new Label(listener.buildStatusLabelText(s), ContentMode.HTML);
            bonusButton.setWidth(6, Unit.EM);
            bonusButton.setHeight(Style._100p);
            bonusButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);

            //l.setSizeFull();
            HorizontalLayout h = new HorizontalLayout();

            h.setSizeFull();
            h.setHeightUndefined();
//            h.setSpacing(true);
//            h.setMargin(new MarginInfo(false, false, false, true));
            h.addComponents(ClearFix.v(), productButton, ClearFix.v(), bonusButton);
            h.setExpandRatio(productButton, 1);
            h.setComponentAlignment(bonusButton, Alignment.MIDDLE_LEFT);
            h.setStyleName(ValoTheme.LAYOUT_CARD);
//            h.setComponentAlignment(bonusButton, Alignment.MIDDLE_LEFT);
            vlist.addComponent(h);
        });
    }

    private HorizontalLayout headCaption() {
        HorizontalLayout head = new HorizontalLayout();
        Label clearFix = new Label("");
        Label productCaption = new Label($(subscribe_log_product_caption));
        Label bonusCaption = new Label($(subscribe_log_bonus_caption));
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

    public void updateTableImpl(Collection<Subscribe> subscribes) {
        table.setItems(subscribes);
//        getItemContainer().removeAllItems();
//        getItemContainer().addAll(subscribes);
    }

    public Grid getTable() {
        if (table == null) {
            table = createTable();
        }
        return table;
    }

    public Grid createTable() {
        Grid table = new Grid();
        // todo restore
//        table.setImmediate(true);
//        table.setContainerDataSource(getItemContainer());
        table.setWidth(Style._100p);
//        table.setWidth("90%");
//        table.setSizeFull();
//        table.addStyleName(ValoTheme.TABLE_BORDERLESS);
//        table.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
//        table.setStyleName(ValoTheme.TABLE_SMALL);
//        table.addStyleName(ValoTheme.TABLE_COMPACT);


        // todo restore
//        table.setSelectable(true);
        table.setColumnReorderingAllowed(false);
//        table.setFooterVisible(true);
//        Responsive.makeResponsive(table);
        setupTable(table);
        return table;
    }

    public void setupTable(Grid table) {

        List<PropertyMetaInfo> propertyIds = Arrays.asList(
                new PropertyMetaInfo(1, SubscribeProperties.startDate, "Дата"  /*,Table.Align.CENTER*/)
                , new PropertyMetaInfo(2, SubscribeProperties.businessProductName, "Услуга" /*,Table.Align.CENTER*/)
                , new PropertyMetaInfo(3, SubscribeProperties.bonus, "Бонус"  /*,Table.Align.CENTER*/)
        );

        Map<String, PropertyMetaInfo> propertyMap = propertyIds.stream()
                .collect(Collectors.toMap(p -> p.toString(), i -> i));

        Object[] viscol = propertyIds.stream().map(PropertyMetaInfo::toString).toArray();
        // todo restore
//        table.setVisibleColumns(viscol);

        table.addColumn("Дата");
        table.addColumn("Услуга");
        table.addColumn("Бонус");

        // todo restore
/*
        propertyIds.stream().forEach(p -> {
            table.setColumnHeader(p.toString(), p.name_ru);
        });

        table.setColumnWidth(SubscribeProperties.startDate.toString(), 90);
//        table.setColumnWidth(SubscribeProperties.businessProductName.toString(), 130);
        table.setColumnWidth(SubscribeProperties.bonus.toString(), 100);

        table.setColumnAlignment(SubscribeProperties.bonus.toString(), Table.Align.RIGHT);
        table.setColumnAlignment(SubscribeProperties.businessProductName.toString(), Table.Align.CENTER);


        table.setFooterVisible(false);


        table.addGeneratedColumn(
                SubscribeProperties.startDate.toString(), new Table.ColumnGenerator() {
                    @Override
                    public Object generateCell(Table source, Object itemId, Object columnId) {
                        Subscribe subscribe = (Subscribe) itemId;
                        Label label = new Label(subscribe.getStartDate().toLocalDateTime().format(Format.dateTimeFormat));
                        label.setHeight(TABLE_COLUM_HEIGHT_EM, Unit.EM);
                        return label;
                    }
                });

        table.addGeneratedColumn(
            SubscribeProperties.businessProductName.toString(), new Table.ColumnGenerator() {
                @Override
                public Object generateCell(Table source, Object itemId, Object columnId) {
                    Subscribe subscribe = (Subscribe) itemId;
                    Label label = new Label(subscribe.getBusinessProductName().getNameRu());
                    label.setHeight(TABLE_COLUM_HEIGHT_EM, Unit.EM);
                    return label;
                }
        });
*/
// todo restore
/*
        table.addGeneratedColumn(
                SubscribeProperties.bonus.toString(), new Table.ColumnGenerator() {
                    @Override
                    public Object generateCell(Table source, Object itemId, Object columnId) {
                        Subscribe subscribe = (Subscribe) itemId;


                        String valueStr = listener.buildStatusLabelText(subscribe);

                        Label label = new Label(valueStr);

                        label.setHeight(TABLE_COLUM_HEIGHT_EM, Unit.EM);
//                        label.setSizeFull();
//                        label.setSizeUndefined();
                        return label;
                    }

                });
*/




/*
        table.addColumnResizeListener(new Table.ColumnResizeListener(){
            public void columnResize(Table.ColumnResizeEvent event) {
                // Get the new width of the resized column
                int width = event.getCurrentWidth();

                // Get the property ID of the resized column
                String column = (String) event.getPropertyId();

                // Do something with the information
                table.setColumnFooter(column, String.valueOf(width) + "px");
            }
        });
*/

// Must be immediate to send the resize events immediately
        // todo restore
//        table.setImmediate(true);
        // TODO add cell column generator decorator
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        hackInputType();
        listener.enter(viewChangeEvent);
    }


    private enum SubscribeProperties {
        startDate, businessProductName, bonus
    }

    class PropertyMetaInfo {

        @Getter
        @Setter
        public Object pid;
        @Getter
        @Setter
        public SubscribeProperties id;
        @Getter
        @Setter
        public String name_ru;
        @Getter
        @Setter
        public String name_kz;
        @Getter
        @Setter
        public String name_en;
        @Getter
        @Setter
        public int order;

        public PropertyMetaInfo(int order, SubscribeProperties id, String name_ru, String name_kz, String name_en) {
            this.id = id;
            this.name_ru = name_ru;
            this.name_kz = name_kz;
            this.name_en = name_en;
            this.order = order;
        }

        public PropertyMetaInfo(int order, SubscribeProperties id, String name_ru) {
            this.id = id;
            this.name_ru = name_ru;
            this.name_kz = name_ru;
            this.name_en = name_ru;
            this.order = order;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PropertyMetaInfo that = (PropertyMetaInfo) o;

            if (!id.equals(that.id)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        @Override
        public String toString() {
            return id.name();
        }
    }
}

