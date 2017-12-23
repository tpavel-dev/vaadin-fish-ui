package kz.kcell.apps.fish.mobile.vaadin;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 10 11 2014
 */
@Slf4j
@ConfigurationProperties(prefix = "spmot.web")
@Component
@Data
@RefreshScope
@ToString
public class SpmotWebConfig /*implements SmartLifecycle*/ {
    public static final String INPUT_PROMPT_CELL = "702 XXXXXXX";

    //    public static Boolean developMode = false;
    private Boolean developMode = false;

    private Captcha captcha = new Captcha();

    private Audit audit = new Audit();

    @PostConstruct
    public void init() {
        log.info("SpmotWebConfig : {}");
    }

    @Data
    @ToString
    public static class Audit {
        public int loginEventsCapacity = 5;

        public int ivrEventsCapacity = 5;
    }

    @Data
    @ToString
    public static class Captcha {
        public Boolean enabled = true;

        public int attemptsAuthorization = 5;

        public int captchaLenght = 4;
    }
}
