package kz.kcell.apps.fish.mobile.vaadin.controller;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Notification;
import kz.kcell.apps.fish.mobile.vaadin.SpmotWebConfig;
import kz.kcell.apps.fish.mobile.vaadin.annotation.SpringPresenter;
import kz.kcell.apps.fish.mobile.vaadin.ui.view.CaptchaView;
import kz.kcell.vaadin.ui.EventBus;
import kz.kcell.vaadin.ui.EventType;
import kz.kcell.vaadin.ui.Presenter;
import lombok.Getter;
import lombok.Setter;
import nl.captcha.Captcha;
import nl.captcha.gimpy.FishEyeGimpyRenderer;
import nl.captcha.text.producer.NumbersAnswerProducer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 02 06 2016
 */
@SpringPresenter
public class CapcthaPresenter extends AbstractPresenter<CaptchaView> implements Presenter, CaptchaView.CaptchaViewListener {

    private static OffsetDateTime odt = OffsetDateTime.now(ZoneId.systemDefault());
    private static ZoneOffset zoneOffset = odt.getOffset();
    //    private Integer magicNum;
    private Captcha captcha;
    @Setter
    @Getter
    private CaptchaView view;
    @Autowired
    private EventBus eventBus;

    @Autowired
    private SpmotWebConfig config;

    private String captchaValue = "";

    @Override
    public void translate() {

    }

    @Override
    public void submit(String value) {
//        view.clearNotification();
        Integer enteredValue = Integer.valueOf(value);
        if (enteredValue >= 10) {
            // clear value
            captchaValue = "";
//            view.showNotification(captchaValue, Notification.Type.HUMANIZED_MESSAGE);
            view.setInputValue(captchaValue);
        } else {
            captchaValue += enteredValue.toString();
            view.setInputValue(captchaValue);
            if (captchaValue.length() >= config.getCaptcha().captchaLenght) {
                String savedInputValue = captchaValue;
                captchaValue = "";
//                view.showNotification(captchaValue, Notification.Type.HUMANIZED_MESSAGE);
                view.setInputValue(captchaValue);

//        if (magicNum.toString().equals(value)) {
                if (captcha.isCorrect(savedInputValue)) {
                    view.showNotification("Верно", Notification.Type.HUMANIZED_MESSAGE);
                    eventBus.post(EventType.CAPCTHA_PASS);
                } else {
                    view.showNotification("Неверно", Notification.Type.HUMANIZED_MESSAGE);
                }
            }
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
//        magicNum = Math.toIntExact(Math.round(Math.random() * 8)) + 1;
//        view.setInputValue("Какая цифра ? " + magicNum);
        view.showNotification("", Notification.Type.HUMANIZED_MESSAGE);

        captcha = new Captcha.Builder(120, 50)
                .addText(new NumbersAnswerProducer(config.getCaptcha().captchaLenght))
//                        .addBackground(new GradiatedBackgroundProducer())
//                        .addNoise(new StraightLineNoiseProducer())
//                        .addNoise(new CurvedLineNoiseProducer())
                .gimp(new FishEyeGimpyRenderer())
//                        .gimp(new ShearGimpyRenderer())
                .addBorder()
                .build();

    }

    @Override
    public StreamResource getCaptchaImg() {
        String fileName = buildNewFileForCaptcha();

        StreamResource png = new StreamResource(
                (StreamResource.StreamSource) () -> {
//            createImage();
                    BufferedImage image = captcha.getImage();

                    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                        ImageIO.write(image, "png", bos);
                        return new ByteArrayInputStream(bos.toByteArray());
                    } catch (IOException e) {
//                e.printStackTrace();
                        return null;
                    }
                }, fileName);

        png.setCacheTime(0);
        return png;
    }

    public void createImage() {
        String text = "Date: " + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());

        BufferedImage bi = new BufferedImage(370, 30, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawChars(text.toCharArray(), 0, text.length(), 10, 20);
    }

    private String buildNewFileForCaptcha() {
        return "captcha-" + LocalDateTime.now().toEpochSecond(zoneOffset) + ".png";
    }


}
