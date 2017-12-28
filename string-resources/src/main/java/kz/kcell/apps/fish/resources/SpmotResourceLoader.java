package kz.kcell.apps.fish.resources;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.resource.ResourceBundle;
import kz.kcell.apps.bonus_cmdr.model.SpmotResourceBundle;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 07 04 2016
 */
@RefreshScope
@ConfigurationProperties("kz.kcell.apps.spmot.resources.SpmotResourceLoader")
@RestController
@Slf4j
public class SpmotResourceLoader implements SmartLifecycle {

    private static Properties properties = new Properties();
    @Autowired
    private Environment env;
    @Getter
    @Setter

    private Map<String, PropertiesResourceBundle> resources;

    @PostConstruct
    public void init() {
//        EnvPropertiesPrinter.printEnv();
//        overridResourcesFromSystemEnviroment();
        updateResources();
    }


    @RequestMapping("/retrieveResources")
    public Object retrieveResources() {
        return resources;
    }

    private void updateResources() {
        if(resources == null) return;

        resources.entrySet().forEach( p -> {
            try {
                SpmotResourceBundle res = SpmotResourceBundle.valueOf(p.getKey());
                if(p.getValue()._en() != null) {
                    log.info("Update reosurce lang en, key {}, value {}", p.getKey(), p.getValue().getEn());
                    res.setEn(p.getValue()._en());
                }
                if(p.getValue()._kk() != null) {
                    log.info("Update reosurce lang kk, key {}, value {}", p.getKey(), p.getValue().getKk());
                    res.setKk(p.getValue()._kk());
                }
                if(p.getValue()._ru() != null) {
                    log.info("Update reosurce lang ru, key {}, value {}", p.getKey(), p.getValue().getRu());
                    res.setRu(p.getValue()._ru());
                }
            } catch (Exception e) {
                log.error("Error loading resource with key {}: value {}; Caused by: {}",
                        p.getKey(), p.getValue(), e.getMessage());
            }
        });
    }


    private static void loadPropertiesFromFile() {
        try {

            String propertiesFileName = getPropertyFromSystem("propertiesFile");
            if (StringUtils.isBlank(propertiesFileName)) return;
            File propFile = new File(propertiesFileName);
            log.info("Properties file {} {}", propFile.exists() ? " find " : " not found ", propFile.getAbsolutePath());

            try (InputStreamReader in = new InputStreamReader(new FileInputStream(propertiesFileName), "UTF8");) {

                properties.load(in);
                properties.forEach((k, v) -> {
                    log.info("Load property '{}':'{}'", k, v);
                });

            }
        } catch (Exception exc) {
            log.error("Load properties failed.{} ", exc.getLocalizedMessage(), exc);
//            throw  exc;
        }
    }

    private static String getPropertyFromSystem(String fqn) {
        return ObjectUtils.firstNonNull(System.getenv(fqn), System.getProperty(fqn));
    }

    private static String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = ResourceManager.class.getClassLoader();
        if (classLoader == null) throw new IllegalStateException("Classloader is null");

        URL url = classLoader.getResource(fileName);
        if (classLoader == null) throw new IllegalStateException("can not get url");

        String innerFileName = url.getFile();
        if (classLoader == null) throw new IllegalStateException("file not found");

        File file = new File(innerFileName);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public void overridResourcesFromSystemEnviroment() {
        log.info("Start ovveride resource.");
        loadPropertiesFromFile();
        Arrays.stream(SpmotResourceBundle.values()).forEach(this::overridResourcesFromSystemEnviroment);
        log.info("Finish ovveride resource.");
    }

    private  String getProperty(String fqn)  {
        return ObjectUtils.firstNonNull(
                env.getProperty(fqn),
                getProperties().getProperty(fqn),
                getPropertyFromSystem(fqn)
        );
    }

    private static Properties getProperties()  {
        if(properties == null) {
            synchronized (ResourceManager.class) {
                loadPropertiesFromFile();
            }
        }
        return properties;
    }


    private void overridResourcesFromSystemEnviroment(ResourceBundle rs) {
        aa(rs, Language.KK);
        aa(rs, Language.RU);
        aa(rs, Language.EN);
    }

    private  void aa(ResourceBundle rs, Language lang) {
        String key = makeSystemEnvKey(rs) + "."+lang.name().toLowerCase();
//        String value = ObjectUtils.firstNonNull(System.getenv(key), System.getProperty(key));
        String value = getValue(key);
//        log.info("Resource {} : '{}' " ,key,rs.getValue(lang) );
        if (value != null) {
            rs.setValue(lang, value);
            log.info("Overide from sysenv {} : '{}' " ,key,rs.getValue(lang) );
        }
    }

    private static String makeSystemEnvKey(ResourceBundle rs) {
        return rs.getClass().getName()+"."+rs.name().toString();
    }


    public  String getValue(String fqn) {
        String value = getProperty(fqn);
//        log.info("{} = {}", fqn, value);
        return value;

    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        log.info(SpmotResourceLoader.class.getSimpleName() + " stop callback.");
    }

    @Override
    public void start() {
        log.info(SpmotResourceLoader.class.getSimpleName() + " start.");
    }

    @Override
    public void stop() {
        log.info(SpmotResourceLoader.class.getSimpleName() + " stop.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
