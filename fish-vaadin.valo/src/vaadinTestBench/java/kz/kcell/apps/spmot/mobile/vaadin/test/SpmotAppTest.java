package test;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.*;
import kz.kcell.apps.spmot.mobile.vaadin.SpmotResourceBundle;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;

import static kz.kcell.apps.spmot.domain.spmot.entity.SpmotResourceBundle.*;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 28 11 2014
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpmotAppTest extends TestBenchTestCase {
    private static final int    SLEEP_TIME = 1;
//    private static final int    SLEEP_TIME = 0;
    private static final String NON_ACTIV_MSISDN = "7012110207";
    private static final String INVALID_MSISDN = "2110207";
    private static final String DEALER_MSISDN_SHORT = "7757507062";
    private static final String DEALER_MSISDN_FULL = "77757507062";
    private static final String SUBSCRIBER_MSISDN_SHORT = "7029912351";
    private static final String SUBSCRIBER_MSISDN_FULL = "77029912351";
    private static final String NON_AUTORIZED_MSISDN = "77029912351";

    private static final String DEALER_PASSWORD = "4la4184p";

    //    static int SLEEP_TIME = 1;
//    static int SLEEP_TIME = 2;
    @Before
    public void setUp() throws Exception {
        FirefoxDriver f = new FirefoxDriver();
        f.manage().window().setSize(new Dimension(1440,900));
        f.manage().window().setPosition(new Point(0,0));
        setDriver(f);
        getDriver().get("http://localhost:9080/spmot-mobile/");
    }

    @After
    public void tearDown() throws Exception {
        delay();
        delay();
        getDriver().quit();
    }

    @Test
    public void allTestLoginForm() {
        testChangeLang();           delay();
        testLoginInvalidMsisdn();   delay();
        testLoginNonactivMsisdn();  delay();
//        testLoginInvalidPassword(); delay();
        testLoginSuccess();
    }

    @Test
    public void testLoginInvalidMsisdn() {
        txt(login_msisdn_field_caption).setValue(INVALID_MSISDN);  delay();
        btn(login_submit_button_caption).click();
        Assert.assertEquals(login_validate_msg_unvalid_msisdn.$(), lbl(notification_label).getText());
    }

    @Test
    public void testLoginNonactivMsisdn() {
        txt(login_msisdn_field_caption).setValue(NON_ACTIV_MSISDN); delay();
        btn(login_submit_button_caption).click();
        Assert.assertEquals(error_invalid_brend.$() ,lbl(notification_label).getText());
    }

    @Test
    public void testLoginNoauthenticatedMsisdn() {
        txt(login_msisdn_field_caption).setValue(DEALER_MSISDN_FULL); delay();
        pss(login_password_field_caption).setValue("bed password");   delay();
        btn(login_submit_button_caption).click();
        Assert.assertEquals(error_not_authenticated.$() ,lbl(notification_label).getText());
    }

    @Test
    public void testLoginNoautorizationMsisdn() {
        txt(login_msisdn_field_caption).setValue(NON_AUTORIZED_MSISDN); delay();
        btn(login_submit_button_caption).click();
        Assert.assertEquals(error_not_authorized.$() ,lbl(notification_label).getText());
    }


/*
    @Test
    public void testLoginInvalidPassword() {
        txt(login_msisdn_field_caption).setValue(DEALER_MSISDN_FULL);  delay();
        pss(login_password_field_caption).setValue("111");      delay();
        btn(login_submit_button_caption).click();
    }
*/

    @Test
    public void testLoginSuccess() {
//        txt(login_msisdn_field_caption).setValue("775750706");  delay();
//        pss(login_password_field_caption).setValue("");         delay();
        txt(login_msisdn_field_caption).setValue(DEALER_MSISDN_SHORT); delay();
        pss(login_password_field_caption).setValue(DEALER_PASSWORD); delay();
        btn(login_submit_button_caption).click();
    }

    @Test
    public void testRunMeinMenuItems() {
        SpmotResourceBundle[] mainMenuItems = new SpmotResourceBundle[]{
                  main_menu_product
                , main_menu_subscribe_log
                , main_menu_transfer_bonus
                , main_menu_change_lang
                , main_menu_bonus_info
                , main_menu_out
        };

        testLoginSuccess();
        Arrays.stream(mainMenuItems).forEach(i ->{btn(i).click(); delay();});
        Arrays.stream(mainMenuItems).sorted((i1, i2) -> i1.compareTo(i2)).forEach(i -> {
            btn(i).click();delay();});
        Arrays.stream(mainMenuItems).sorted((i1, i2) -> i1.compareTo(i2) * -1).forEach(i -> {
            btn(i).click(); delay();});

/*
        btn(main_menu_product        ).click(); delay();
        btn(main_menu_subscribe_log  ).click(); delay();
        btn(main_menu_transfer_bonus ).click(); delay();
        btn(main_menu_change_lang    ).click(); delay();
        btn(main_menu_out            ).click();
*/
    }

    @Test
    public void testTransferForm() {
        testLoginSuccess();
        btn(main_menu_transfer_bonus).click();                          delay();

        txt(transfer_msisdn_field_caption).setValue("");               delay();
        txt(transfer_sum_field_caption).setValue("");                  delay();
        btn(transfer_confirm_field_caption).click();                   delay();
        Assert.assertEquals(transfer_validate_msg_enter_msisdn.$(), lbl(notification_label).getText());

        txt(transfer_msisdn_field_caption).setValue(INVALID_MSISDN);    delay();
        btn(transfer_confirm_field_caption).click();                    delay();
        Assert.assertEquals(transfer_validate_msg_unvalid_msisdn.$(), lbl(notification_label).getText());

        txt(transfer_msisdn_field_caption).setValue(DEALER_MSISDN_SHORT);    delay();
        txt(transfer_sum_field_caption).setValue("");                        delay();
        btn(transfer_confirm_field_caption).click();                         delay();
        Assert.assertEquals(transfer_validate_msg_check_sum.$(), lbl(notification_label).getText());

        txt(transfer_msisdn_field_caption).setValue(SUBSCRIBER_MSISDN_FULL); delay();
        txt(transfer_sum_field_caption).setValue("");                        delay();
        btn(transfer_confirm_field_caption).click();                         delay();
        Assert.assertEquals(transfer_validate_msg_check_sum.$(), lbl(notification_label).getText());

        txt(transfer_sum_field_caption).setValue("asda");               delay();
        btn(transfer_confirm_field_caption).click();                    delay();
        Assert.assertEquals(transfer_validate_msg_check_sum.$(), lbl(notification_label).getText());

        txt(transfer_sum_field_caption).setValue("0");                  delay();
        btn(transfer_confirm_field_caption).click();                    delay();
        Assert.assertEquals(transfer_validate_msg_check_sum.$(), lbl(notification_label).getText());

        txt(transfer_sum_field_caption).setValue("-1");                 delay();
        btn(transfer_confirm_field_caption).click();                    delay();
        Assert.assertEquals(transfer_validate_msg_check_sum.$(), lbl(notification_label).getText());

        txt(transfer_sum_field_caption).setValue("1");                  delay();
        btn(transfer_confirm_field_caption).click();                    delay();
        btn(transfer_confirm_cancel_btn_caption).click();               delay();
        Assert.assertEquals(transfer_reject_notify.$(), lbl(notification_label).getText());

        txt(transfer_msisdn_field_caption).setValue(NON_ACTIV_MSISDN);  delay();
        txt(transfer_sum_field_caption).setValue("1");                  delay();
        btn(transfer_confirm_field_caption).click();                    delay();
        btn(transfer_confirm_submit_btn_caption).click();               delay();
        Assert.assertEquals(error_invalid_brend.$(), lbl(notification_label).getText());

        txt(transfer_msisdn_field_caption).setValue(SUBSCRIBER_MSISDN_SHORT);  delay();
        txt(transfer_sum_field_caption).setValue("1");                  delay();
        btn(transfer_confirm_field_caption).click();                    delay();
        btn(transfer_confirm_submit_btn_caption).click();               delay();
        Assert.assertEquals(transfer_notify_msg_transfer_successfull.$(), lbl(notification_label).getText());

//        btn(transfer_request_bonus_caption).click();
    }

    @Test
    // todo add asserts
    public void testSubscribeLogForm() {
        testLoginSuccess();
        btn(main_menu_subscribe_log).click();        delay();
        dt(subscribe_log_to).setValue("01/11/14");   delay();
        dt(subscribe_log_to).submit();               delay();
//        btn(subscribe_log_submit).click(); delay();
        dt(subscribe_log_from).setValue("03/11/14"); delay();
        dt(subscribe_log_from).submit();             delay();
//        btn(subscribe_log_submit).click(); delay();
        dt(subscribe_log_to).setValue("10/12/14");   delay();
        dt(subscribe_log_to).submit();               delay();
//        btn(subscribe_log_submit).click(); delay();
        dt(subscribe_log_from).setValue("01/11/14"); delay();
        dt(subscribe_log_from).submit();               delay();
//        btn(subscribe_log_submit).click(); delay();
        dt(subscribe_log_from).setValue("01/12/14"); delay();
        dt(subscribe_log_from).submit();               delay();
//        btn(subscribe_log_submit).click(); delay();

//        btn(subscribe_log_submit).click();


    }

    @Test
    // todo add scroll
    // todo add asserts
    public void testRunByProductNavForm() {
        testLoginSuccess();
        btn(main_menu_product).click(); //sleep(3);
//        Integer[] pbIds = new Integer[]{77,94,95,103,107,108,121,122,123,124,146,147,148};
        Integer[] pbIds = new Integer[]{148, 147, 146};
        Arrays.stream(pbIds).sorted((i1, i2)-> i1.compareTo(i2)).forEach(id -> {
            btn(product_productCaption_label,id).click(); delay();
//            Assert.assertEquals(Boolean.TRUE, Boolean.valueOf(lbl(product_subscribe_form_title).isDisplayed()));
            btn(main_menu_product).click();               delay();

        });
    }

    @Test
    public void testRunByProductIvrForm() {
        testLoginSuccess();
//        Integer[] pbIds = new Integer[]{77,94,95,103,107,108,121,122,123,124,146,147,148};
        Integer[] pbIds = new Integer[]{148, 147, 146};
        Arrays.stream(pbIds).sorted((i1, i2)-> i1.compareTo(i2)).forEach(id -> {
            btn(main_menu_product).click();               delay();
            btn(product_productCaption_label,id).click(); delay();

            txt(product_subscribe_form_msisdn_field_caption).setValue(NON_ACTIV_MSISDN);  delay();
            btn(product_subscribe_form_submit_btn_caption).click();                    delay();
            Assert.assertEquals(error_invalid_brend.$(), lbl(notification_label).getText());

            txt(product_subscribe_form_msisdn_field_caption).setValue(INVALID_MSISDN);    delay();
            btn(product_subscribe_form_submit_btn_caption).click();                       delay();
            Assert.assertEquals(product_subscribe_form_validate_msg2.$(), lbl(notification_label).getText());

            txt(product_subscribe_form_msisdn_field_caption).setValue("");    delay();
            btn(product_subscribe_form_submit_btn_caption).click();           delay();
            Assert.assertEquals(product_subscribe_form_validate_msg2.$(), lbl(notification_label).getText());

            txt(product_subscribe_form_msisdn_field_caption).setValue(DEALER_MSISDN_SHORT);    delay();
            btn(product_subscribe_form_submit_btn_caption).click();                            delay();
            Assert.assertEquals(error_try_subscribe_self.$(), lbl(notification_label).getText());

            txt(product_subscribe_form_msisdn_field_caption).setValue(SUBSCRIBER_MSISDN_FULL); delay();
            btn(product_subscribe_form_submit_btn_caption).click();                    delay();
            Assert.assertEquals(product_ivr_view_title.$(), lbl(title_label).getText());
        });
    }

    @Test
    public void testChangeLangForm() {
        testLoginSuccess();
        btn(main_menu_change_lang).click(); delay();
        btn(language_name_ru).click();      delay();
        btn(language_name_kk).click();      delay();
        btn(language_name_ru).click();      delay();
        btn(language_name_kk).click();      delay();
        btn(language_name_ru).click();
    }

    @Test
    public void testBonusInfo() {
        testLoginSuccess();
        btn(main_menu_bonus_info).click(); delay();
        btn(transfer_request_bonus_caption).click();
    }

    @Test
    public void testLoginWithoutId() {
        $(TextFieldElement.class).first().setValue("7757507062");
        $(ButtonElement.class).first().click();
    }

    @Test
    public void testChangeLang() {
        btn(language_name_ru).click();  delay();
        btn(language_name_kk).click();  delay();
        btn(language_name_ru).click();
    }

    public ButtonElement btn(SpmotResourceBundle id, Object suffix) {
        return btn(id.name()+suffix.toString());
    }

    public ButtonElement btn(SpmotResourceBundle id) {
        return btn(id.name());
    }

    public ButtonElement btn(String id) {
        return $(ButtonElement.class).id(id);
    }

    public TextFieldElement txt(SpmotResourceBundle id) {
        return $(TextFieldElement.class).id(id.name());
    }

    public PasswordFieldElement pss(SpmotResourceBundle id) {
        return $(PasswordFieldElement.class).id(id.name());
    }

    public LabelElement lbl(SpmotResourceBundle id) {
        return $(LabelElement.class).id(id.name());
    }

    public DateFieldElement dt(SpmotResourceBundle id) {
        return $(DateFieldElement.class).id(id.name());
    }

    private void delay() {
        sleep(SLEEP_TIME);
    }

    private void sleep(int c) {
        if(c <= 0) return;
        try {
            Thread.sleep(c+1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
