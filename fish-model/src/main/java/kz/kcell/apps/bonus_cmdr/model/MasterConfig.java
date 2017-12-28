package kz.kcell.apps.bonus_cmdr.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bonus_cmdr")
@Data
public class MasterConfig {
    private String dataDirName;

}
