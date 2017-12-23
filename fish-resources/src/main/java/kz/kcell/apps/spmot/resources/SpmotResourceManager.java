package kz.kcell.apps.spmot.resources;

import kz.kcell.apps.spmot.domain.spmot.entity.SpmotResourceBundle;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 07 04 2016
 */
@Slf4j
public class SpmotResourceManager extends ResourceManager {

/*
    static {
        overridResourcesFromSystemEnviroment();
    }
*/

    public SpmotResourceManager() {
//        overridResourcesFromSystemEnviroment();
    }

}
